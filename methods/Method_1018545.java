static CborObject read(InputStream in,int maxBytes){
  return deserialize(new CborDecoder(in),maxBytes);
}
