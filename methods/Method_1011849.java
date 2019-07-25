public boolean contains(T value){
  if (myStart != null) {
    int deltaStart=myStart.compareTo(value);
    if (myStartIncluded) {
      if (deltaStart > 0) {
        return false;
      }
    }
 else {
      if (deltaStart >= 0) {
        return false;
      }
    }
  }
  if (myEnd != null) {
    int deltaEnd=myEnd.compareTo(value);
    if (myEndIncluded) {
      if (deltaEnd < 0) {
        return false;
      }
    }
 else {
      if (deltaEnd <= 0) {
        return false;
      }
    }
  }
  return true;
}
