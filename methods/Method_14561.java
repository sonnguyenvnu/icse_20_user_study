@Override protected String getBriefDescription(Project project){
  return "Columnize by key column " + _keyColumnName + " and value column " + _valueColumnName + (_noteColumnName != null ? (" with note column " + _noteColumnName) : "");
}
