private boolean hasMatch(String filename){
  for (  String query : mLogger.getKeyCollisionStackTraceKeywords()) {
    if (filename.contains(query)) {
      return true;
    }
  }
  return false;
}
