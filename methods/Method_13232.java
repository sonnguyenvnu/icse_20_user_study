protected void init(Properties properties){
  String selectors=properties.getProperty("selectors");
  externalSelectors=(selectors == null) ? null : Arrays.asList(selectors.split(","));
  String pathRegExp=properties.getProperty("pathRegExp");
  externalPathPattern=(pathRegExp == null) ? null : Pattern.compile(pathRegExp);
}
