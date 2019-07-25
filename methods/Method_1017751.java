boolean intersects(Segment other){
  Orientation o1=orientation(a,b,other.a);
  Orientation o2=orientation(a,b,other.b);
  Orientation o3=orientation(other.a,other.b,a);
  Orientation o4=orientation(other.a,other.b,b);
  if (o1 != o2 && o3 != o4)   return true;
  if (o1 == COLLINEAR && other.a.between(a,b))   return true;
  if (o2 == COLLINEAR && other.b.between(a,b))   return true;
  if (o3 == COLLINEAR && a.between(other.a,other.b))   return true;
  if (o4 == COLLINEAR && b.between(other.a,other.b))   return true;
  return false;
}
