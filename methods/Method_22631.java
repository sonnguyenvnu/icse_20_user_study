public void saveLanguage(String language){
  String[] cmdarray=new String[]{"defaults","write",System.getProperty("user.home") + "/Library/Preferences/org.processing.app","AppleLanguages","-array",language};
  try {
    Runtime.getRuntime().exec(cmdarray);
  }
 catch (  IOException e) {
    Messages.log("Error saving platform language: " + e.getMessage());
  }
}
