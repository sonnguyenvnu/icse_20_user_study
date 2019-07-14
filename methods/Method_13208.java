protected void init(Properties properties){
  String selectors=properties.getProperty("selectors");
  if (selectors != null) {
    externalSelectors=Arrays.asList(selectors.split(","));
  }
  String pathRegExp=properties.getProperty("pathRegExp");
  if (pathRegExp != null) {
    externalPathPattern=Pattern.compile(pathRegExp);
  }
}
