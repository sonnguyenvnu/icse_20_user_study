/** 
 * get the size of a smb file
 * @param file
 * @param timeout
 * @return
 * @throws IOException
 */
public static long length(final SmbFile file,final long timeout) throws IOException {
  try {
    return new TimeoutRequest<Long>(new Callable<Long>(){
      @Override public Long call(){
        try {
          return file.length();
        }
 catch (        final SmbException e) {
          return Long.valueOf(0);
        }
      }
    }
).call(timeout).longValue();
  }
 catch (  final ExecutionException e) {
    throw new IOException(file.toString() + ":" + e.getMessage());
  }
}
