private void validateCRUDCheckPresent(final AbstractApexNode<?> node,final Object data,final String crudMethod,final String typeCheck){
  if (!typeToDMLOperationMapping.containsKey(typeCheck)) {
    if (!isProperESAPICheckForDML(typeCheck,crudMethod)) {
      addViolation(data,node);
    }
  }
 else {
    boolean properChecksHappened=false;
    List<String> dmlOperationsChecked=typeToDMLOperationMapping.get(typeCheck);
    for (    String dmlOp : dmlOperationsChecked) {
      if (dmlOp.equalsIgnoreCase(crudMethod)) {
        properChecksHappened=true;
        break;
      }
      if (crudMethod.equals(ANY)) {
        properChecksHappened=true;
        break;
      }
    }
    if (!properChecksHappened) {
      addViolation(data,node);
    }
  }
}
