/** 
 * Adds a point to the path by moving to the specified coordinates specified in float precision. <p> This method provides a single precision variant of the double precision {@code moveTo()} method on the base {@code LinePath} class.
 * @param x the specified X coordinate
 * @param y the specified Y coordinate
 * @see LinePath#moveTo
 */
public final void moveTo(float x,float y,int c){
  if (numTypes > 0 && pointTypes[numTypes - 1] == SEG_MOVETO) {
    floatCoords[numCoords - 2]=x;
    floatCoords[numCoords - 1]=y;
    pointColors[numCoords / 2 - 1]=c;
  }
 else {
    needRoom(false,1);
    pointTypes[numTypes++]=SEG_MOVETO;
    floatCoords[numCoords++]=x;
    floatCoords[numCoords++]=y;
    pointColors[numCoords / 2 - 1]=c;
  }
}
