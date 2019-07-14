@Override public void logInsert(String tag,int index,RenderInfo renderInfo,String thread){
  for (  SectionsDebugLogger sectionsDebugLogger : mSectionsDebugLoggers) {
    sectionsDebugLogger.logInsert(tag,index,renderInfo,thread);
  }
}
