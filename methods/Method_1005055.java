@Override public boolean accept(final Key key,final Value value){
  final boolean foundDelimiter=hasDelimiter(key);
  if (!edges && foundDelimiter) {
    return false;
  }
 else   if (!entities && !foundDelimiter) {
    return false;
  }
  return true;
}
