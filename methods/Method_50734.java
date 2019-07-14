private void reportIfHardCoded(Object data,Object potentialIV){
  if (potentialIV instanceof ASTVariableExpression) {
    ASTVariableExpression variable=(ASTVariableExpression)potentialIV;
    if (potentiallyStaticBlob.contains(Helper.getFQVariableName(variable))) {
      addViolation(data,variable);
    }
  }
}
