public static StartDiagramExtractReader build(FileWithSuffix f2,StringLocated s,String charset){
  return new StartDiagramExtractReader(getReadLine(f2,s,charset),f2.getSuffix());
}
