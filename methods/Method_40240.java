public static String projRelPath(String file){
  if (file.startsWith(Analyzer.self.projectDir)) {
    return file.substring(Analyzer.self.projectDir.length() + 1);
  }
 else {
    return file;
  }
}
