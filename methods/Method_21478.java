private void handleGeoBoundsAggregation(List<String> headers,List<List<String>> lines,GeoBounds geoBoundsAggregation){
  String geoBoundAggName=geoBoundsAggregation.getName();
  headers.add(geoBoundAggName + ".topLeft.lon");
  headers.add(geoBoundAggName + ".topLeft.lat");
  headers.add(geoBoundAggName + ".bottomRight.lon");
  headers.add(geoBoundAggName + ".bottomRight.lat");
  List<String> line=lines.get(this.currentLineIndex);
  line.add(String.valueOf(geoBoundsAggregation.topLeft().getLon()));
  line.add(String.valueOf(geoBoundsAggregation.topLeft().getLat()));
  line.add(String.valueOf(geoBoundsAggregation.bottomRight().getLon()));
  line.add(String.valueOf(geoBoundsAggregation.bottomRight().getLat()));
  lines.add(line);
}
