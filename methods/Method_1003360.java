@Override public Value add(Value v){
  ValueFloat v2=(ValueFloat)v;
  return get(value + v2.value);
}
