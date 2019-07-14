public String getHomeDir(){
  if (homeDir == null) {
    homeDir=System.getProperty("user.home");
    String sudoUser=System.getenv("SUDO_USER");
    if (sudoUser != null && sudoUser.length() != 0) {
      try {
        homeDir=getHomeDir(sudoUser);
      }
 catch (      Exception e) {
      }
    }
  }
  return homeDir;
}
