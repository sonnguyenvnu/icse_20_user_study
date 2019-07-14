public void checkValue(E value){
  if (!choicesByLabel.containsValue(value)) {
    throw new IllegalArgumentException("Invalid default value: no mapping to this value");
  }
}
