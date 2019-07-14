protected void processValue(Object value,int index){
  if (ExpressionUtils.isError(value)) {
    hasError=true;
  }
 else   if (ExpressionUtils.isNonBlankData(value)) {
    String valueString=StringUtils.toString(value);
    IndexedNominalFacetChoice facetChoice=choices.get(valueString);
    if (facetChoice != null) {
      if (facetChoice._latestIndex < index) {
        facetChoice._latestIndex=index;
        facetChoice.count++;
      }
    }
 else {
      String label=valueString;
      DecoratedValue dValue=new DecoratedValue(value,label);
      IndexedNominalFacetChoice choice=new IndexedNominalFacetChoice(dValue,index);
      choice.count=1;
      choices.put(valueString,choice);
    }
  }
 else {
    hasBlank=true;
  }
}
