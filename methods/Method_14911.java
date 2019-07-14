public void saveCurrentUser(User user){
  if (user == null) {
    Log.e(TAG,"saveCurrentUser  currentUser == null >> return;");
    return;
  }
  if (user.getId() <= 0 && StringUtil.isNotEmpty(user.getName(),true) == false) {
    Log.e(TAG,"saveCurrentUser  user.getId() <= 0" + " && StringUtil.isNotEmpty(user.getName(), true) == false >> return;");
    return;
  }
  if (currentUser != null && user.getId().equals(currentUser.getId()) && StringUtil.isNotEmpty(user.getPhone(),true) == false) {
    user.setPhone(currentUser.getPhone());
  }
  currentUser=user;
  DataManager.getInstance().saveCurrentUser(currentUser);
}
