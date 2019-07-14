/** 
 * Controls logging of debug messages. All instances are affected.
 * @param debug true to enable debug logging, false to disable.
 */
@Keep @SuppressWarnings("unused") public static void setDebug(boolean debug){
  SkiaPooledImageRegionDecoder.debug=debug;
}
