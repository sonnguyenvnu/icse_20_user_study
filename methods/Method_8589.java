protected float getBezierCoordinateY(float time){
  c.y=3 * start.y;
  b.y=3 * (end.y - start.y) - c.y;
  a.y=1 - c.y - b.y;
  return time * (c.y + time * (b.y + time * a.y));
}
