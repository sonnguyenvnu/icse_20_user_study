/** 
 * Merges this rectangle with the other rectangle. The rectangle should not have negative width or negative height.
 * @param rect the other rectangle
 * @return this rectangle for chaining 
 */
public Rectangle merge(Rectangle rect){
  float minX=Math.min(x,rect.x);
  float maxX=Math.max(x + width,rect.x + rect.width);
  x=minX;
  width=maxX - minX;
  float minY=Math.min(y,rect.y);
  float maxY=Math.max(y + height,rect.y + rect.height);
  y=minY;
  height=maxY - minY;
  return this;
}
