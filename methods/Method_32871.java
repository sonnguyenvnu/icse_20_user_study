/** 
 * Block set characters. All characters must be within the bounds of the screen, or else and InvalidParemeterException will be thrown. Typically this is called with a "val" argument of 32 to clear a block of characters.
 */
public void blockSet(int sx,int sy,int w,int h,int val,long style){
  if (sx < 0 || sx + w > mColumns || sy < 0 || sy + h > mScreenRows) {
    throw new IllegalArgumentException("Illegal arguments! blockSet(" + sx + ", " + sy + ", " + w + ", " + h + ", " + val + ", " + mColumns + ", " + mScreenRows + ")");
  }
  for (int y=0; y < h; y++)   for (int x=0; x < w; x++)   setChar(sx + x,sy + y,val,style);
}
