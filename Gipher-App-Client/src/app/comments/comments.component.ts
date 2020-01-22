import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { GiphyAPIService } from '../services/giphy-api.service';
import { Comment } from '../model/Comment';
import { AuthService } from '../services/auth.service';
import { Gif } from '../model/Gif';

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent implements OnInit {
  commentGroup: FormGroup;
  commentControl: FormControl;
  @Input() gifId: string;
  commentDetails: Comment = new Comment();
  commentsArray: Array<Comment>;


  constructor(private commentService: GiphyAPIService, private authService: AuthService) {
    this.commentsArray = [];
  }

  ngOnInit() {
    this.commentControl = new FormControl('');
    this.commentGroup = new FormGroup({
      commentControl: this.commentControl
    });
    this.fetchAllComments();
  }

  fetchAllComments() {
    this.commentService.fetchAllComments(this.gifId).subscribe(
      data => {
        this.commentsArray = data;
        var tempComment: Comment;
        for (let i = 0; i < this.commentsArray.length; i++) {
          for (let j = i + 1; j < this.commentsArray.length; j++) {
            if (this.commentsArray[j].likedBy.length > this.commentsArray[i].likedBy.length) {
              tempComment = this.commentsArray[i];
              this.commentsArray[i] = this.commentsArray[j];
              this.commentsArray[j] = tempComment;
            }
          }
        }
        console.log(this.commentsArray);
      },
      error => console.log(error.message)
    );
  }
  onAddComment() {
    var commentControl = this.commentGroup.get("commentControl").value;
    if (commentControl !== '') {
      var commentData = {
        "content": commentControl,
        "userEmail": this.authService.loggedInUser.emailId,
        "gifId": this.gifId,
        "likedBy": []
      }
      this.commentService.addComments(commentData).subscribe(
        data => this.fetchAllComments(),
        error => console.log(error.message)
      )
      this.commentGroup.reset();
    }
  }
}

