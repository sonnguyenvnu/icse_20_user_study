public void invoke(Object from,BytesArray to){
  if (from instanceof Text) {
    Text t=(Text)from;
    to.bytes(t.getBytes(),t.getLength());
  }
  if (from instanceof BytesWritable) {
    BytesWritable b=(BytesWritable)from;
    to.bytes(b.getBytes(),b.getLength());
  }
}
