private void copyLengthFromLoader(ExtractingLoadable loadable){
  if (length == C.LENGTH_UNSET) {
    length=loadable.length;
  }
}
