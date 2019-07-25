public static StartDiagramExtractReader build(InputStream is,StringLocated s,String desc){
  return new StartDiagramExtractReader(getReadLine(is,s,desc),null);
}
