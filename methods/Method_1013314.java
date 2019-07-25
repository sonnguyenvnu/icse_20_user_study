private byte[] read(final File file){
  if (file.isDirectory())   throw new RuntimeException("Unsupported operation, file " + file.getAbsolutePath() + " is a directory");
  if (file.length() > Integer.MAX_VALUE)   throw new RuntimeException("Unsupported operation, file " + file.getAbsolutePath() + " is too big");
  Throwable pending=null;
  FileInputStream in=null;
  final byte buffer[]=new byte[(int)file.length()];
  try {
    in=new FileInputStream(file);
    in.read(buffer);
  }
 catch (  Exception e) {
    pending=new RuntimeException("Exception occured on reading file " + file.getAbsolutePath(),e);
  }
 finally {
    if (in != null) {
      try {
        in.close();
      }
 catch (      Exception e) {
        if (pending == null) {
          pending=new RuntimeException("Exception occured on closing file" + file.getAbsolutePath(),e);
        }
      }
    }
    if (pending != null) {
      throw new RuntimeException(pending);
    }
  }
  return buffer;
}
