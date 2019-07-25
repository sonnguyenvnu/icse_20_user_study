@Override public Value divide(final Value v){
  final Value iv=v.convertTo(Value.INT);
  return convertTo(Value.INT).divide(iv);
}
