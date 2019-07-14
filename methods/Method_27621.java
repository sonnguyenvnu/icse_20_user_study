@NonNull @Override public ArrayList<String> getNamesToTag(){
  return CommentsHelper.getUsers(adapter.getData());
}
