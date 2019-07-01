@Override public void _XXXXX_(){
  for (  UserManager userManager : userManagerPerId.values()) {
    userManager.eraseDatabase();
  }
}