@Override public Value negate(){
  if (value == Long.MIN_VALUE) {
    throw getOverflow();
  }
  return ValueLong.get(-value);
}
