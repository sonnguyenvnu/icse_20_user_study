/** 
 * <p>Detects a rectangular region of black and white -- mostly black -- with a region of mostly white, in an image.</p>
 * @return {@link ResultPoint}[] describing the corners of the rectangular region. The first and last points are opposed on the diagonal, as are the second and third. The first point will be the topmost point and the last, the bottommost. The second point will be leftmost and the third, the rightmost
 * @throws NotFoundException if no Data Matrix Code can be found
 */
public ResultPoint[] detect() throws NotFoundException {
  int height=image.getHeight();
  int width=image.getWidth();
  int halfHeight=height / 2;
  int halfWidth=width / 2;
  int deltaY=Math.max(1,height / (MAX_MODULES * 8));
  int deltaX=Math.max(1,width / (MAX_MODULES * 8));
  int top=0;
  int bottom=height;
  int left=0;
  int right=width;
  ResultPoint pointA=findCornerFromCenter(halfWidth,0,left,right,halfHeight,-deltaY,top,bottom,halfWidth / 2);
  top=(int)pointA.getY() - 1;
  ResultPoint pointB=findCornerFromCenter(halfWidth,-deltaX,left,right,halfHeight,0,top,bottom,halfHeight / 2);
  left=(int)pointB.getX() - 1;
  ResultPoint pointC=findCornerFromCenter(halfWidth,deltaX,left,right,halfHeight,0,top,bottom,halfHeight / 2);
  right=(int)pointC.getX() + 1;
  ResultPoint pointD=findCornerFromCenter(halfWidth,0,left,right,halfHeight,deltaY,top,bottom,halfWidth / 2);
  bottom=(int)pointD.getY() + 1;
  pointA=findCornerFromCenter(halfWidth,0,left,right,halfHeight,-deltaY,top,bottom,halfWidth / 4);
  return new ResultPoint[]{pointA,pointB,pointC,pointD};
}
