@Override public void logUpdate(String tag,int index,RenderInfo renderInfo,String thread){
  for (  SectionsDebugLogger sectionsDebugLogger : mSectionsDebugLoggers) {
    sectionsDebugLogger.logUpdate(tag,index,renderInfo,thread);
  }
}
