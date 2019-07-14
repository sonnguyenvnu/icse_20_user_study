private StaticBuffer adjustToLength(StaticBuffer key){
  if (hasFixedKeyLength() && key.length() != keyLength) {
    if (key.length() > keyLength) {
      return key.subrange(0,keyLength);
    }
 else {
      return BufferUtil.padBuffer(key,keyLength);
    }
  }
  return key;
}
