import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { GiphyAPIService } from '../services/giphy-api.service';
import { Comment } from '../model/Comment';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-comment-card',
  templateUrl: './comment-card.component.html',
  styleUrls: ['./comment-card.component.css']
})
export class CommentCardComponent implements OnInit {
  @Input() comment: Comment;
  @Output() refreshCommentEvent: EventEmitter<any> = new EventEmitter();
  likeText: string;
  constructor(private commentService: GiphyAPIService, private authService: AuthService) {
  }

  ngOnInit() {
    if (this.comment.likedBy.findIndex(email => email === this.authService.loggedInUser.emailId) === -1) {
      this.likeText = "LIKE";
    } else {
      this.likeText = "DISLIKE";
    }
  }

  onLikeClick(id: string) {
    this.commentService.fetchCommentById(id).subscribe(
      comment => {
        var index = -1;
        if (comment.likedBy !== null) {
          index = comment.likedBy.findIndex(email => email === this.authService.loggedInUser.emailId);
        }
        if (index === -1) {
          comment.likedBy.push(this.authService.loggedInUser.emailId);
          this.commentService.addComments(comment).subscribe(
            data => this.refreshCommentEvent.emit(null),
            error => console.log(error.message)
          );
          this.likeText = "DISLIKE";
        } else {
          comment.likedBy.splice(index, 1);
          this.commentService.addComments(comment).subscribe(
            data => this.refreshCommentEvent.emit(null),
            error => console.log(error.message)
          );
          this.likeText = "LIKE";
        }
      }
    );
  }
  deleteComment(id: string) {
    this.commentService.deleteComments(id).subscribe(
      data => this.refreshCommentEvent.emit(null),
      error => console.log(error.message)
    );
  }
  isUserAuthenticatedToDelete() {
    if (this.comment.userEmail === this.authService.loggedInUser.emailId) {
      return true;
    } else {
      return false;
    }
  }
}

