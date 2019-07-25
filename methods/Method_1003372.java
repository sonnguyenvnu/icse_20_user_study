@Override public Value add(Value v){
  throw DbException.getUnsupportedException("manipulating TIMESTAMP WITH TIME ZONE values is unsupported");
}
