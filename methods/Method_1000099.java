public void put(BlockId id){
  put(ByteArray.fromLong(id.getNum()),new BytesCapsule(id.getBytes()));
}
