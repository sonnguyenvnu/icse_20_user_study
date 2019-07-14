public void geoadd(String key,Map<String,GeoCoordinate> memberCoordinateMap){
  geoadd(SafeEncoder.encode(key),convertMemberCoordinateMapToBinary(memberCoordinateMap));
}
