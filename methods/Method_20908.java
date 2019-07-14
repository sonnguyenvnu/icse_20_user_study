public static @NonNull CommentsEnvelope commentsEnvelope(){
  return CommentsEnvelope.builder().urls(CommentsEnvelope.UrlsEnvelope.builder().api(CommentsEnvelope.UrlsEnvelope.ApiEnvelope.builder().moreComments("http://kck.str/comments/more").newerComments("http://kck.str/comments/newer").build()).build()).comments(Collections.singletonList(CommentFactory.comment())).build();
}
