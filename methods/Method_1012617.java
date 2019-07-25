@Override public void load(Map<String,String> options){
  setSQLExpression(options.get(SQLEXPRESSION));
  setExecutionThreshold(options.get(EXECUTION_THRESHOLD));
  setExcludecategories(options.get(EXCLUDECATEGORIES));
  setFilter(options.get(FILTER));
  setInclude(options.get(INCLUDE));
  setExclude(options.get(EXCLUDE));
  setExcludebinary(options.get(EXCLUDEBINARY));
}
