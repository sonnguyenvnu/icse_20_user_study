public String errorFor(E value){
  return labelsByChoice.containsKey(value) ? null : nonLegalValueMsgFor(value);
}
