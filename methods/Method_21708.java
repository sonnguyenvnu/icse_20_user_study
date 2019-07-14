private boolean isValidMultiSelectReturnFields(){
  List<Field> firstQueryFields=multiQuerySelect.getFirstSelect().getFields();
  List<Field> secondQueryFields=multiQuerySelect.getSecondSelect().getFields();
  if (firstQueryFields.size() > secondQueryFields.size()) {
    return isSubsetFields(firstQueryFields,secondQueryFields);
  }
  return isSubsetFields(secondQueryFields,firstQueryFields);
}
