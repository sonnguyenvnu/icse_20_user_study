private static void addSegment(long startTimeUs,String baseUrl,RangedUri rangedUri,ArrayList<Segment> out){
  DataSpec dataSpec=new DataSpec(rangedUri.resolveUri(baseUrl),rangedUri.start,rangedUri.length,null);
  out.add(new Segment(startTimeUs,dataSpec));
}
