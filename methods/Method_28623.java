private List<byte[]> convertGeoCoordinateMapToByteArrays(Map<byte[],GeoCoordinate> memberCoordinateMap){
  List<byte[]> args=new ArrayList<byte[]>(memberCoordinateMap.size() * 3);
  for (  Entry<byte[],GeoCoordinate> entry : memberCoordinateMap.entrySet()) {
    GeoCoordinate coordinate=entry.getValue();
    args.add(toByteArray(coordinate.getLongitude()));
    args.add(toByteArray(coordinate.getLatitude()));
    args.add(entry.getKey());
  }
  return args;
}
