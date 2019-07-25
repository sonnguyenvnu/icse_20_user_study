public boolean contains(Rectangle2D rect){
  GeneralPath path=makeIntoPath();
  if (path != null)   return path.contains(rect);
  return false;
}
