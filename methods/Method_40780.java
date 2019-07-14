public static String projAbsPath(String file){
  if (file.startsWith("/") || file.startsWith(Analyzer.self.projectDir)) {
    return file;
  }
 else {
    return makePathString(Analyzer.self.projectDir,file);
  }
}
