@Override public Value negate(){
  if (leading == 0L && remaining == 0L) {
    return this;
  }
  return Value.cache(new ValueInterval(valueType,!negative,leading,remaining));
}
