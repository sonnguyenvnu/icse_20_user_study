private MocoEventAction doCreateAction(){
  if (get != null) {
    return get.createAction();
  }
  if (post != null) {
    return post.createAction();
  }
  throw new IllegalArgumentException("At least one action is expected");
}
