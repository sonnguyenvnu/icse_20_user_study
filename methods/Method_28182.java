@NonNull @Override public ArrayList<String> getNamesToTag(){
  return CommentsHelper.getUsersByTimeline(adapter.getData());
}
