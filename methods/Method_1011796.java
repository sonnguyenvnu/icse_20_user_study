public boolean includes(double arg){
  if (arg < myStart || arg > myEnd) {
    return false;
  }
  if (arg > myStart && arg < myEnd) {
    return true;
  }
  if (arg == myStart) {
    return myStartIncluded;
  }
  if (arg == myEnd) {
    return myEndIncluded;
  }
  assert false : "unreachable";
  return false;
}
