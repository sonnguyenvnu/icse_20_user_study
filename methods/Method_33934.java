static HeaderParameters parseParams(byte[] header){
  Map<String,String> map=parseMap(utf8Decode(header));
  return new HeaderParameters(map);
}
