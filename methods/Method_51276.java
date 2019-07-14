private void initRuleQueryBasedOnVersion(final String version){
  xpathRuleQuery=XPATH_1_0.equals(version) ? new JaxenXPathRuleQuery() : new SaxonXPathRuleQuery();
}
