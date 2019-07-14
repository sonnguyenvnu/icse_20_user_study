@NonNull @Override public ArrayList<String> getNamesToTags(){
  return CommentsHelper.getUsersByTimeline(adapter.getData());
}
