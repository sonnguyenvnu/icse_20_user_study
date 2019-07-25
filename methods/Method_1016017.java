public static void init(final File mimeFile){
  if (mimeTable.isEmpty()) {
    BufferedInputStream mimeTableInputStream=null;
    try {
      mimeTableInputStream=new BufferedInputStream(new FileInputStream(mimeFile));
      mimeTable.load(mimeTableInputStream);
    }
 catch (    final Exception e) {
    }
 finally {
      if (mimeTableInputStream != null)       try {
        mimeTableInputStream.close();
      }
 catch (      final Exception e1) {
      }
    }
  }
  for (  Entry<Object,Object> entry : mimeTable.entrySet()) {
    String ext=(String)entry.getKey();
    String mime=(String)entry.getValue();
    if (mime.startsWith("text/"))     textExtSet.add(ext.toLowerCase());
    if (mime.startsWith("audio/"))     audioExtSet.add(ext.toLowerCase());
    if (mime.startsWith("video/"))     videoExtSet.add(ext.toLowerCase());
    if (mime.startsWith("application/"))     appsExtSet.add(ext.toLowerCase());
  }
}
