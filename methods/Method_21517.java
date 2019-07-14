private boolean checkIfOnlyOneField(Select firstSelect,Select secondSelect){
  return firstSelect.getFields().size() == 1 && secondSelect.getFields().size() == 1;
}
