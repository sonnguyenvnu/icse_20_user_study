@Override public ApolloDataSource getObject() throws Exception {
  return new ApolloDataSource(namespaceName,flowRulesKey,defaultFlowRuleValue,converter);
}
