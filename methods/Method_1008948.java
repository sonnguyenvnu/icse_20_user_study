public OpcPackage get(InputStream is) throws Docx4JException {
  HashMap<String,ByteArray> partByteArrays=new HashMap<String,ByteArray>();
  try {
    ZipInputStream zis=new ZipInputStream(is);
    ZipEntry entry=null;
    while ((entry=zis.getNextEntry()) != null) {
      byte[] bytes=getBytesFromInputStream(zis);
      partByteArrays.put(entry.getName(),new ByteArray(bytes));
    }
    zis.close();
  }
 catch (  Exception e) {
    throw new Docx4JException("Error processing zip file (is it a zip file?)",e);
  }
  return process(partByteArrays);
}
