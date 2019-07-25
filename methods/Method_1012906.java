@Override protected LoadType<FeedbackResponseComment> load(){
  return ofy().load().type(FeedbackResponseComment.class);
}
