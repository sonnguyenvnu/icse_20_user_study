public int replaceValue(String value,String newValue){
  if (value == null) {
    for (int i=0; i < count; i++) {
      if (data[i] == null) {
        data[i]=newValue;
        return i;
      }
    }
  }
 else {
    for (int i=0; i < count; i++) {
      if (value.equals(data[i])) {
        data[i]=newValue;
        return i;
      }
    }
  }
  return -1;
}
