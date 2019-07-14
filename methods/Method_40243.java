public static String locateTmp(String file){
  String tmpDir=getSystemTempDir();
  return makePathString(tmpDir,"pysonar2",file + "." + Analyzer.self.sid);
}
