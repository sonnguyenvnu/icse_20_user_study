public int getFlags(){
  int flags=0;
  if (searchInConstantPoolsCheckBoxType.isSelected())   flags+=SEARCH_TYPE;
  if (searchInConstantPoolsCheckBoxConstructor.isSelected())   flags+=SEARCH_CONSTRUCTOR;
  if (searchInConstantPoolsCheckBoxMethod.isSelected())   flags+=SEARCH_METHOD;
  if (searchInConstantPoolsCheckBoxField.isSelected())   flags+=SEARCH_FIELD;
  if (searchInConstantPoolsCheckBoxString.isSelected())   flags+=SEARCH_STRING;
  if (searchInConstantPoolsCheckBoxModule.isSelected())   flags+=SEARCH_MODULE;
  if (searchInConstantPoolsCheckBoxDeclarations.isSelected())   flags+=SEARCH_DECLARATION;
  if (searchInConstantPoolsCheckBoxReferences.isSelected())   flags+=SEARCH_REFERENCE;
  return flags;
}
