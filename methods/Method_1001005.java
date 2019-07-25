protected static <T>boolean _equals(T value1,T value2){
  if (value1 == value2) {
    return true;
  }
  return (value1 != null) && value1.equals(value2);
}
