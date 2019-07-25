public boolean intersect(Slot other){
  return contains(other.start) || contains(other.end) || other.contains(start) || other.contains(end);
}
