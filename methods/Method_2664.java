public boolean save(String fileName){
  DataOutputStream out;
  try {
    out=new DataOutputStream(new BufferedOutputStream(IOUtil.newOutputStream(fileName)));
    out.writeInt(size);
    for (int i=0; i < size; i++) {
      out.writeInt(base[i]);
      out.writeInt(check[i]);
    }
    out.close();
  }
 catch (  Exception e) {
    return false;
  }
  return true;
}
