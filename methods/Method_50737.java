@Override public Object visit(final ASTProperty node,Object data){
  ASTField field=node.getFirstChildOfType(ASTField.class);
  if (field != null) {
    String fieldType=field.getNode().getFieldInfo().getType().getApexName();
    addVariableToMapping(Helper.getFQVariableName(field),fieldType);
  }
  return data;
}
