/** 
 * ??????
 * @param outFile
 * @param files
 */
public static void mergeFiles(Context context,File outFile,List<File> files){
  FileChannel outChannel=null;
  try {
    outChannel=new FileOutputStream(outFile).getChannel();
    for (    File f : files) {
      FileChannel fc=new FileInputStream(f).getChannel();
      ByteBuffer bb=ByteBuffer.allocate(BUFSIZE);
      while (fc.read(bb) != -1) {
        bb.flip();
        outChannel.write(bb);
        bb.clear();
      }
      fc.close();
    }
    Log.d(TAG,"????");
  }
 catch (  IOException ioe) {
    ioe.printStackTrace();
  }
 finally {
    try {
      if (outChannel != null) {
        outChannel.close();
      }
    }
 catch (    IOException ignore) {
    }
  }
}
