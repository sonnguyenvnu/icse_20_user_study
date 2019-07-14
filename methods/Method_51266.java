@Override public List<String> getRuleChainVisits(){
  try {
    initializeExpressionIfStatusIsNoneOrPartial(null);
    return super.getRuleChainVisits();
  }
 catch (  final JaxenException ex) {
    throw new RuntimeException(ex);
  }
}
