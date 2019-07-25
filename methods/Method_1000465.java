public void truncate(long len) throws SQLException {
  try {
    RandomAccessFile raf=new RandomAccessFile(file,"rw");
    raf.setLength(len);
    raf.close();
  }
 catch (  FileNotFoundException e) {
    throw Lang.wrapThrow(e);
  }
catch (  IOException e) {
    throw Lang.wrapThrow(e);
  }
}
