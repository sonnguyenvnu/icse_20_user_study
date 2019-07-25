/** 
 * @param circle the circle
 * @return whether the circle is contained in the rectangle 
 */
public boolean contains(Circle circle){
  return (circle.x - circle.radius >= x) && (circle.x + circle.radius <= x + width) && (circle.y - circle.radius >= y) && (circle.y + circle.radius <= y + height);
}
