@Override public Value multiply(final Value v){
  final Value iv=v.convertTo(Value.INT);
  return convertTo(Value.INT).multiply(iv);
}
