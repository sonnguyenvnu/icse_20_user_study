/** 
 * Downloads resource to a file, potentially very efficiently.
 */
public static void downloadFile(final String url,final File file) throws IOException {
  try (InputStream inputStream=new URL(url).openStream();ReadableByteChannel rbc=Channels.newChannel(inputStream);FileChannel fileChannel=FileChannel.open(file.toPath(),StandardOpenOption.CREATE,StandardOpenOption.TRUNCATE_EXISTING,StandardOpenOption.WRITE)){
    fileChannel.transferFrom(rbc,0,Long.MAX_VALUE);
  }
 }
