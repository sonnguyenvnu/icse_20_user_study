/** 
 * Clockwise rotation, about the bounding-box centre
 * @param units  are 60 thousandths of a degree
 */
public void rotate(int units){
  Point centre=new Point(Math.round(offset.x + 0.5f * extent.x),Math.round(offset.y + 0.5f * extent.y));
  float degree=-units / 60000;
  System.out.println("Rotating " + degree);
  float radians=(float)Math.toRadians(degree);
  Point otherCorner=getOtherCorner();
  Point otherCornerDash=otherCorner.subtract(centre);
  Point otherCornerDash2=rotate(otherCornerDash,radians);
  Point otherCornerDash3=otherCornerDash2.add(centre);
  Point offsetDash=offset.subtract(centre);
  Point offsetDash2=rotate(offsetDash,radians);
  Point offsetDash3=offsetDash2.add(centre);
  offset=offsetDash3;
  extent=otherCornerDash3.subtract(offset);
  System.out.println("Rotated--> " + debug());
}
