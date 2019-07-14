public String minKey(){
  checkMinMax("minKey");
  int index=minIndex();
  if (index == -1) {
    return null;
  }
  return keys[index];
}
