private boolean isProperESAPICheckForDML(final String typeToCheck,final String dmlOperation){
  final boolean hasMapping=checkedTypeToDMLOperationViaESAPI.containsKey(typeToCheck.toString());
  if (hasMapping) {
    if (dmlOperation.equals(ANY)) {
      return true;
    }
    String dmlChecked=checkedTypeToDMLOperationViaESAPI.get(typeToCheck);
    return dmlChecked.equals(dmlOperation);
  }
  return false;
}
