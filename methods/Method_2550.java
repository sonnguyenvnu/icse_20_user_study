boolean isValidOffset(int id,int offset){
  if (extras(offset).isUsed) {
    return false;
  }
  int relOffset=id ^ offset;
  if ((relOffset & LOWER_MASK) != 0 && (relOffset & UPPER_MASK) != 0) {
    return false;
  }
  for (int i=1; i < _labels.size(); ++i) {
    if (extras(offset ^ (_labels.get(i) & 0xFF)).isFixed) {
      return false;
    }
  }
  return true;
}
