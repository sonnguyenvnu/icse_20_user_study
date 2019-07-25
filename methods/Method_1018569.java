@SuppressWarnings("unusable-by-js") public void serialize(OutputStream out){
  try {
    putUvarint(out,type.index);
    putUvarint(out,hash.length);
    out.write(hash);
  }
 catch (  IOException e) {
    throw new RuntimeException(e);
  }
}
