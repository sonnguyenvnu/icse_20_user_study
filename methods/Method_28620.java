public void geoadd(byte[] key,Map<byte[],GeoCoordinate> memberCoordinateMap){
  List<byte[]> args=new ArrayList<byte[]>(memberCoordinateMap.size() * 3 + 1);
  args.add(key);
  args.addAll(convertGeoCoordinateMapToByteArrays(memberCoordinateMap));
  byte[][] argsArray=new byte[args.size()][];
  args.toArray(argsArray);
  sendCommand(GEOADD,argsArray);
}
