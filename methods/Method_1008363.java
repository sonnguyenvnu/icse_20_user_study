public static StreamInput wrap(byte[] bytes,int offset,int length){
  return new InputStreamStreamInput(new ByteArrayInputStream(bytes,offset,length),length);
}
