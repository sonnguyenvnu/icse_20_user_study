private boolean isLegalPath(String path,LanguageConfig config){
  String[] extensions=config.extensions();
  for (int i=0; i < extensions.length; i++) {
    if (path.endsWith(extensions[i]) && !extensions[i].isEmpty()) {
      return true;
    }
  }
  return false;
}
