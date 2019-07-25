private static void skip(ByteBuffer input,int bytes){
  input.position(input.position() + bytes);
}
