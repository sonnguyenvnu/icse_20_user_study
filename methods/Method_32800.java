private static boolean shouldAddLdLibraryPath(){
  try (BufferedReader in=new BufferedReader(new InputStreamReader(new FileInputStream(TermuxService.PREFIX_PATH + "/etc/apt/sources.list")))){
    String line;
    while ((line=in.readLine()) != null) {
      if (!line.startsWith("#") && line.contains("https://dl.bintray.com/termux/termux-packages-24")) {
        return false;
      }
    }
  }
 catch (  IOException e) {
    Log.e(LOG_TAG,"Error trying to read sources.list",e);
  }
  return true;
}
