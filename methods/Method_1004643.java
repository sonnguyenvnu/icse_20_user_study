public static synchronized void register(int identityNumber,AbstractValueDecoder decoder){
  Map<Integer,AbstractValueDecoder> newMap=new HashMap<>();
  newMap.putAll(decoderMap);
  newMap.put(identityNumber,decoder);
  decoderMap=newMap;
}
