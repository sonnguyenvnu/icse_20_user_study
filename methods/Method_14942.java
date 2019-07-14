private void setCurrentUser(){
  currentUser=APIJSONApplication.getInstance().getCurrentUser();
  currentUserId=currentUser == null ? 0 : currentUser.getId();
  isLoggedIn=isCurrentUserCorrect();
}
