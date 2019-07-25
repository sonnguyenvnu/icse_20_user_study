@Override public Value add(Value v){
  ValueDouble v2=(ValueDouble)v;
  return get(value + v2.value);
}
