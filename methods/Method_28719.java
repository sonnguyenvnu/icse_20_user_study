private HashMap<byte[],GeoCoordinate> convertMemberCoordinateMapToBinary(Map<String,GeoCoordinate> memberCoordinateMap){
  HashMap<byte[],GeoCoordinate> binaryMemberCoordinateMap=new HashMap<byte[],GeoCoordinate>();
  for (  Entry<String,GeoCoordinate> entry : memberCoordinateMap.entrySet()) {
    binaryMemberCoordinateMap.put(SafeEncoder.encode(entry.getKey()),entry.getValue());
  }
  return binaryMemberCoordinateMap;
}
