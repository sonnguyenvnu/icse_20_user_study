@Override public Value atomize(){
  if (value == null) {
    value=SaxonXPathRuleQuery.getAtomicRepresentation(attribute.getValue());
  }
  return value;
}
