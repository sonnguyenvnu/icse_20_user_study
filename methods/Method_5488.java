@Override protected List<Segment> getSegments(DataSource dataSource,SsManifest manifest,boolean allowIncompleteList){
  ArrayList<Segment> segments=new ArrayList<>();
  for (  StreamElement streamElement : manifest.streamElements) {
    for (int i=0; i < streamElement.formats.length; i++) {
      for (int j=0; j < streamElement.chunkCount; j++) {
        segments.add(new Segment(streamElement.getStartTimeUs(j),new DataSpec(streamElement.buildRequestUri(i,j))));
      }
    }
  }
  return segments;
}
