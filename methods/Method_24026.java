protected static void getByteArray(ByteBuffer buf,byte[] arr){
  if (!buf.hasArray() || buf.array() != arr) {
    buf.position(0);
    buf.get(arr);
    buf.rewind();
  }
}
