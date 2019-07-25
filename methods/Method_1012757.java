/** 
 * Read and discard the  {@link InputStream} in the caller's context(blocking). Use this when you want to wait for the stream to be consumed. Note that you cannot handle more than one stream at a time with this, and thus any process output to be gobbled should be created with {@link ProcessBuilder} and it's error stream redirected.
 * @param in the {@link InputStream} to be consumed
 */
public static void consume(InputStream in){
  consume(in,false);
}
