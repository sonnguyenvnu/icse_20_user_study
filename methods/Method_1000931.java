@Override public boolean equals(Object o){
  if (o == this)   return true;
  if (o == null)   return false;
  if (!(o instanceof BooleanNode)) {
    return false;
  }
  return (_value == ((BooleanNode)o)._value);
}
