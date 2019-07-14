@OnUpdateState static void updateState(StateValue<Boolean> commentText){
  commentText.set(commentText.get() == true ? false : true);
}
