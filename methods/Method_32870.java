/** 
 * Block copy characters from one position in the screen to another. The two positions can overlap. All characters of the source and destination must be within the bounds of the screen, or else an InvalidParameterException will be thrown.
 * @param sx source X coordinate
 * @param sy source Y coordinate
 * @param w  width
 * @param h  height
 * @param dx destination X coordinate
 * @param dy destination Y coordinate
 */
public void blockCopy(int sx,int sy,int w,int h,int dx,int dy){
  if (w == 0)   return;
  if (sx < 0 || sx + w > mColumns || sy < 0 || sy + h > mScreenRows || dx < 0 || dx + w > mColumns || dy < 0 || dy + h > mScreenRows)   throw new IllegalArgumentException();
  boolean copyingUp=sy > dy;
  for (int y=0; y < h; y++) {
    int y2=copyingUp ? y : (h - (y + 1));
    TerminalRow sourceRow=allocateFullLineIfNecessary(externalToInternalRow(sy + y2));
    allocateFullLineIfNecessary(externalToInternalRow(dy + y2)).copyInterval(sourceRow,sx,sx + w,dx);
  }
}
