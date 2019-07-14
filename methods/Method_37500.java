/** 
 * Read file and returns byte array with contents.
 * @param file  {@link File} to read
 * @param count number of bytes to read
 * @return byte array from {@link File} contents.
 * @throws IOException if not a {@link File} or {@link File} does not exist or file size islarger than  {@link Integer#MAX_VALUE}.
 */
public static byte[] readBytes(final File file,final int count) throws IOException {
  checkExists(file);
  checkIsFile(file);
  long numToRead=file.length();
  if (numToRead >= Integer.MAX_VALUE) {
    throw new IOException("File is larger then max array size");
  }
  if (count > NEGATIVE_ONE && count < numToRead) {
    numToRead=count;
  }
  byte[] bytes=new byte[(int)numToRead];
  RandomAccessFile randomAccessFile=new RandomAccessFile(file,"r");
  randomAccessFile.readFully(bytes);
  randomAccessFile.close();
  return bytes;
}
