@Override public Value interp(Scope s){
  Value vector=value.interp(s);
  Value indexValue=index.interp(s);
  if (!(vector instanceof Vector)) {
    _.abort(value,"subscripting non-vector: " + vector);
    return null;
  }
  if (!(indexValue instanceof IntValue)) {
    _.abort(value,"subscript " + index + " is not an integer: " + indexValue);
    return null;
  }
  List<Value> values=((Vector)vector).values;
  int i=((IntValue)indexValue).value;
  if (i >= 0 && i < values.size()) {
    return values.get(i);
  }
 else {
    _.abort(this,"subscript out of bound: " + i + " v.s. [0, " + (values.size() - 1) + "]");
    return null;
  }
}
