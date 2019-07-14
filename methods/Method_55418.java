/** 
 * Retrieves the position of the mouse cursor, in screen coordinates.
 * @param point a pointer to a {@link POINT} structure that receives the screen coordinates of the cursor
 */
@NativeType("BOOL") public static boolean GetCursorPos(@NativeType("LPPOINT") POINT point){
  return nGetCursorPos(point.address()) != 0;
}
