public static Cid cast(byte[] data){
  if (data.length == 34 && data[0] == 18 && data[1] == 32)   return buildV0(Multihash.decode(data));
  InputStream in=new ByteArrayInputStream(data);
  try {
    long version=readVarint(in);
    if (version != 0 && version != 1)     throw new CidEncodingException("Invalid Cid version number: " + version);
    long codec=readVarint(in);
    Multihash hash=Multihash.deserialize(in);
    return new Cid(version,Codec.lookup(codec),hash.type,hash.getHash());
  }
 catch (  Exception e) {
    throw new CidEncodingException("Invalid cid bytes: " + ArrayOps.bytesToHex(data));
  }
}
