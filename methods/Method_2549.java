int findValidOffset(int id){
  if (_extrasHead >= _units.size()) {
    return _units.size() | (id & LOWER_MASK);
  }
  int unfixedId=_extrasHead;
  do {
    int offset=unfixedId ^ (_labels.get(0) & 0xFF);
    if (isValidOffset(id,offset)) {
      return offset;
    }
    unfixedId=extras(unfixedId).next;
  }
 while (unfixedId != _extrasHead);
  return _units.size() | (id & LOWER_MASK);
}
