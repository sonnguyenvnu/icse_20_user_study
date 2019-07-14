public void logout(){
  currentUser=null;
  DataManager.getInstance().saveCurrentUser(currentUser);
}
