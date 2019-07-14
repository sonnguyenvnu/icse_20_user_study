/** 
 * Starts reading the log such that it will start with the first entry written after now.
 * @return
 */
public static ReadMarker fromNow(){
  return new ReadMarker(null,null);
}
