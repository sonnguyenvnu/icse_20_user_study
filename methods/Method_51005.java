private RegionByOffset mapToRegionByOffset(final RegionByLine regionByLine){
  final int startOffset=mapToOffset(regionByLine.getBeginLine(),regionByLine.getBeginColumn());
  final int endOffset=mapToOffset(regionByLine.getEndLine(),regionByLine.getEndColumn());
  return new RegionByOffsetImp(startOffset,endOffset - startOffset);
}
