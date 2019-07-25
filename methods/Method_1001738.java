public static StartDiagramExtractReader build(URL url,StringLocated s,String uid,String charset){
  return new StartDiagramExtractReader(getReadLine(url,s,charset),uid);
}
