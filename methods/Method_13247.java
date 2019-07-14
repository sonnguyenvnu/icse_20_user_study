@Override public byte[] load(String internalName) throws LoaderException {
  InputStream is=this.getClass().getResourceAsStream("/" + internalName + ".class");
  if (is == null) {
    return null;
  }
 else {
    try (InputStream input=is;ByteArrayOutputStream output=new ByteArrayOutputStream()){
      int len=input.read(buffer);
      while (len > 0) {
        output.write(buffer,0,len);
        len=input.read(buffer);
      }
      return output.toByteArray();
    }
 catch (    IOException e) {
      throw new LoaderException(e);
    }
  }
}
