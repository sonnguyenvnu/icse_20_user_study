@Override public void logShouldUpdate(String tag,Object previous,Object next,String previousPrefix,String nextPrefix,Boolean shouldUpdate,String thread){
  for (  SectionsDebugLogger sectionsDebugLogger : mSectionsDebugLoggers) {
    sectionsDebugLogger.logShouldUpdate(tag,previous,next,previousPrefix,nextPrefix,shouldUpdate,thread);
  }
}
