public Path resolve(String other){
  if (pathString.endsWith(SEPARATOR))   return new Path(pathString + other);
  return new Path(pathString + "/" + other);
}
