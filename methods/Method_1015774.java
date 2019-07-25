public static String print(ByteBuffer buf){
  return buf == null ? "null" : String.format("[pos=%d lim=%d cap=%d]",buf.position(),buf.limit(),buf.capacity());
}
