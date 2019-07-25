@Override public T combine(T parentValue,T currentValue){
  if (currentValue != null) {
    return currentValue;
  }
  return myDefaultValue;
}
