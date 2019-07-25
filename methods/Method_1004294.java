/** 
 * Attempts to exhaust  {@code source}, returning true if successful. This is useful when reading a complete source is helpful, such as when doing so completes a cache body or frees a socket connection for reuse. <p>Copied from OkHttp.
 */
static boolean discard(Source source,int timeout,TimeUnit timeUnit){
  try {
    return skipAll(source,timeout,timeUnit);
  }
 catch (  IOException e) {
    return false;
  }
}
