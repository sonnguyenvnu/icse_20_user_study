protected void postProcessGrouper(ExpressionNominalValueGrouper grouper){
  _choices.clear();
  _choices.addAll(grouper.choices.values());
  for (  DecoratedValue decoratedValue : _config.selection) {
    String valueString=decoratedValue.value.toString();
    if (grouper.choices.containsKey(valueString)) {
      grouper.choices.get(valueString).selected=true;
    }
 else {
      NominalFacetChoice choice=new NominalFacetChoice(decoratedValue);
      choice.count=0;
      choice.selected=true;
      _choices.add(choice);
    }
  }
  _blankCount=grouper.blankCount;
  _errorCount=grouper.errorCount;
}
