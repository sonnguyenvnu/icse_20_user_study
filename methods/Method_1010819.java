@Override public T combine(T parentValue,T currentValue){
  if (currentValue == null) {
    if (parentValue != null) {
      return parentValue;
    }
 else {
      return myDefaultValue;
    }
  }
 else {
    return currentValue;
  }
}
