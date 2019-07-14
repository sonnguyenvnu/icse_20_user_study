public static StaticBuffer hashPrefixKey(final HashLength hashPrefixLen,final StaticBuffer key){
  final int prefixLen=hashPrefixLen.length();
  final StaticBuffer.Factory<HashCode> hashFactory;
switch (hashPrefixLen) {
case SHORT:
    hashFactory=SHORT_HASH_FACTORY;
  break;
case LONG:
hashFactory=LONG_HASH_FACTORY;
break;
default :
throw new IllegalArgumentException("Unknown hash prefix: " + hashPrefixLen);
}
HashCode hashcode=key.as(hashFactory);
WriteByteBuffer newKey=new WriteByteBuffer(prefixLen + key.length());
assert prefixLen == 4 || prefixLen == 8;
if (prefixLen == 4) newKey.putInt(hashcode.asInt());
 else newKey.putLong(hashcode.asLong());
newKey.putBytes(key);
return newKey.getStaticBuffer();
}
