/** 
 * Adds a point to the path by drawing a straight line from the current coordinates to the new specified coordinates specified in float precision. <p> This method provides a single precision variant of the double precision {@code lineTo()} method on the base {@code LinePath} class.
 * @param x the specified X coordinate
 * @param y the specified Y coordinate
 * @see LinePath#lineTo
 */
public final void lineTo(float x,float y,int c){
  needRoom(true,1);
  pointTypes[numTypes++]=SEG_LINETO;
  floatCoords[numCoords++]=x;
  floatCoords[numCoords++]=y;
  pointColors[numCoords / 2 - 1]=c;
}
