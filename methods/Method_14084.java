public Integer getChoiceValueCount(Object choiceValue){
  if (ExpressionUtils.isError(choiceValue)) {
    return errorCount;
  }
 else   if (ExpressionUtils.isNonBlankData(choiceValue)) {
    IndexedNominalFacetChoice choice=choices.get(StringUtils.toString(choiceValue));
    return choice != null ? choice.count : 0;
  }
 else {
    return blankCount;
  }
}
