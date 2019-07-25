public boolean contains(ShapePoint point){
  GeneralPath path=makeIntoPath();
  if (path != null)   return path.contains(point);
  return false;
}
