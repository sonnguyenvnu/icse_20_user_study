public static String locateTmp(String file){
  String tmpDir=getSystemTempDir();
  return makePathString(tmpDir,"rubysonar",file + "." + Analyzer.self.sid);
}
