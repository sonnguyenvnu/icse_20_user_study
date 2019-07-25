public OpcPackage get(File f) throws Docx4JException {
  log.info("Filepath = " + f.getPath());
  ZipFile zf=null;
  try {
    if (!f.exists()) {
      log.info("Couldn't find " + f.getPath());
    }
    zf=new ZipFile(f);
  }
 catch (  IOException ioe) {
    ioe.printStackTrace();
    throw new Docx4JException("Couldn't get ZipFile",ioe);
  }
  HashMap<String,ByteArray> partByteArrays=new HashMap<String,ByteArray>();
  Enumeration entries=zf.entries();
  while (entries.hasMoreElements()) {
    ZipEntry entry=(ZipEntry)entries.nextElement();
    log.info("\n\n" + entry.getName() + "\n");
    InputStream in=null;
    try {
      byte[] bytes=getBytesFromInputStream(zf.getInputStream(entry));
      partByteArrays.put(entry.getName(),new ByteArray(bytes));
    }
 catch (    Exception e) {
      e.printStackTrace();
    }
  }
  try {
    zf.close();
  }
 catch (  IOException exc) {
    exc.printStackTrace();
  }
  return process(partByteArrays);
}
