private StaticBuffer string2StaticBuffer(final String s){
  ByteBuffer out=ByteBuffer.wrap(s.getBytes(Charset.forName("UTF-8")));
  return StaticArrayBuffer.of(out);
}
