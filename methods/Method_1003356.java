@Override public Value multiply(Value v){
  ValueDouble v2=(ValueDouble)v;
  return get(value * v2.value);
}
