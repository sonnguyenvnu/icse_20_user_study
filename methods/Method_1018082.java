@Override public Path resolve(String other){
  Path otherPath=get(other);
  return resolve(otherPath);
}
