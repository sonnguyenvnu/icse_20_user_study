/** 
 * Unsafe version of  {@link #cameras}. 
 */
public static int ncameras(long struct){
  return UNSAFE.getInt(null,struct + AIMemoryInfo.CAMERAS);
}
