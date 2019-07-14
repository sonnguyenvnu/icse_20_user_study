private Observable<Post> sanitizeData(RedditData redditData){
  return Observable.fromIterable(redditData.data().children()).map(Children::data);
}
