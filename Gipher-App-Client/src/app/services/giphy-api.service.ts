import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Gif, Giffy } from '../model/Gif';
import { Comment } from '../model/Comment';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';
@Injectable({
  providedIn: 'root'
})
export class GiphyAPIService {
  baseURL: string;
  apiKey: string;
  selectedGif: Gif;
  trending: Array<Gif>;
  favourites: Array<Giffy>;
  searchedGifs: Array<Gif>;
  recommended: Array<Gif>;
  baseurl: string;

  constructor(private httpClient: HttpClient, private authService: AuthService) {
    this.baseurl = "http://localhost:8081/comment";
    this.apiKey = "P2gwfjvjCmIPbZDSbVsJNx4K8ue1XjPH";
    this.trending = [];
    this.searchedGifs = [];
    this.selectedGif = null;
    this.recommended = [];
  }

  initializeAndPush(data: any): Array<Gif> {
    var gifArray: Array<Gif> = [];
    data["data"].forEach(gif => {
      this.selectedGif = new Gif();
      this.selectedGif.id = gif["id"];
      this.selectedGif.username = gif["username"];
      this.selectedGif.title = gif["title"];
      this.selectedGif.isSticker = gif["is_sticker"];
      this.selectedGif.imageURL = gif["images"]["downsized_large"]["url"];

      gifArray.push(this.selectedGif);
    });

    return gifArray;
  }

  fetchTrendingGifs(typeChoice: number) {
    if (typeChoice == 0) {
      this.httpClient.get(`http://api.giphy.com/v1/gifs/trending?api_key=${this.apiKey}`).subscribe(
        data => {
          this.trending = this.initializeAndPush(data);
          console.log(this.trending);
        },
        error => console.log(error.message)
      );
    } else if (typeChoice == 1) {
      this.httpClient.get(`http://api.giphy.com/v1/stickers/trending?api_key=${this.apiKey}`).subscribe(
        data => {
          this.trending = this.initializeAndPush(data);
        },
        error => console.log(error.message)
      );
    }
  }

  fetchSearchedGif(searchString: string, typeChoice: number) {
    if (typeChoice == 0) {
      this.httpClient.get(`http://api.giphy.com/v1/gifs/search?api_key=${this.apiKey}&q=${searchString}`).subscribe(
        data => this.searchedGifs = this.initializeAndPush(data),
        error => console.log(error.message)
      );
    } else if (typeChoice == 1) {
      this.httpClient.get(`http://api.giphy.com/v1/stickers/search?api_key=${this.apiKey}&q=${searchString}`).subscribe(
        data => this.searchedGifs = this.initializeAndPush(data),
        error => console.log(error.message)
      );
    }
  }

  fetchGifById(gifId: string): Observable<any> {
    return this.httpClient.get<any>(`http://api.giphy.com/v1/gifs/${gifId}?api_key=${this.apiKey}`);
  }

  addComments(commentdata): Observable<Comment> {
    return this.httpClient.post<Comment>('http://localhost:8081/comment/addNewComment', commentdata);
  }

  fetchAllComments(gifId: string): Observable<Array<Comment>> {
    return this.httpClient.get<Array<Comment>>(`http://localhost:8081/comment/getCommentsByGifId/${gifId}`);
  }

  fetchCommentById(commentId: string): Observable<Comment> {
    return this.httpClient.get<Comment>(`http://localhost:8081/comment/getCommentsById/${commentId}`);
  }

  deleteComments(id: string): Observable<String> {
    return this.httpClient.delete(`http://localhost:8081/comment/deleteComment/${id}`, ({ responseType: "text" }));
  }

  addRecommendations(gif: Gif): Observable<Gif> {
    return this.httpClient.post<Gif>('http://localhost:8083/recommendation/addRecommendation', gif);
  }

  fetchRecommendedByGifId(gifId: string): Observable<Gif> {
    return this.httpClient.get<Gif>(`http://localhost:8083/recommendation/fetchByGifId/${gifId}`);
  }

  fetchAllRecommended() {
    this.httpClient.get<Array<Gif>>("http://localhost:8083/recommendation/fetchAll").subscribe(
      data => { this.recommended = data; console.log(this.recommended) },
      error => console.log(error.message)
    );
  }

  deleteRecommendedByGifId(gifId: string): Observable<string> {
    return this.httpClient.delete(`http://localhost:8083/recommendation/deleteByGifId/${gifId}`, ({ responseType: "text" }));
  }

  fetchAllFavourites() {
    this.httpClient.get<Array<Giffy>>("http://localhost:8082/favourites/getAllFavourites").subscribe(
      data => { this.favourites = data; console.log(this.favourites) },
      error => console.log(error.message)
    );
  }

  addToFavourites(gif: Giffy) {
    console.log(gif);
    this.httpClient.post<Giffy>('http://localhost:8082/favourites/addToFavourites', gif).subscribe(
      data => {
        console.log(data);
        this.fetchGifByEmailId(gif.emailId);
        console.log(this.favourites);
      },
      error => console.log(error.message)
    );
  }
  deleteFromFavourites(gif: Giffy) {
    this.httpClient.delete(`http://localhost:8082/favourites/deleteFromFavourites/${gif.favId}`).subscribe(
      data => this.fetchGifByEmailId(gif.emailId),
      error => console.log(error.message)
    )
  }
  fetchGifByEmailId(emailId: string) {
    this.httpClient.get<any>(`http://localhost:8082/favourites/getAllFavouritesByEmailId/${emailId}`).subscribe(data => {
      console.log(data);
      this.favourites = data;
    })
  }
  fetchCommentsByUserEmail(): Observable<Array<Comment>> {
    return this.httpClient.get<Array<Comment>>(`${this.baseurl}/getCommentsByUserEmail/${this.authService.getToken()}`);
  }
}
