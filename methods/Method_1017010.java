public boolean overlap(DateRange other){
  if (end < other.start) {
    return false;
  }
  if (start > other.end) {
    return false;
  }
  return true;
}
