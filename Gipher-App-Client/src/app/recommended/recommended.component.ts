import { Component, OnInit, Input } from '@angular/core';
import { GiphyAPIService } from '../services/giphy-api.service';
import { Gif } from '../model/Gif';

@Component({
  selector: 'app-recommended',
  templateUrl: './recommended.component.html',
  styleUrls: ['./recommended.component.css']
})
export class RecommendedComponent implements OnInit {

  constructor(private gifApi: GiphyAPIService) { }

  ngOnInit() {
  }

  onRecommend() {

  }

}
