@Override public Value subtract(Value v){
  ValueFloat v2=(ValueFloat)v;
  return get(value - v2.value);
}
