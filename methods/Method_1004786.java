@Override public void convert(Object from,BytesArray to){
  if (from instanceof byte[]) {
    byte[] bytes=(byte[])from;
    to.bytes(bytes,bytes.length);
    return;
  }
  if (from instanceof BytesArray) {
    ((BytesArray)from).copyTo(to);
    return;
  }
  to.bytes(from.toString());
}
