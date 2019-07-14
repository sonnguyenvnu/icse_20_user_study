@Override public void apply(final Document document){
  final RegionByLine regionByLine=getRegionByLine();
  document.insert(regionByLine.getBeginLine(),regionByLine.getBeginColumn(),textToInsert);
}
