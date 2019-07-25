static AsyncReader build(byte[] data){
  return new ArrayBacked(data);
}
