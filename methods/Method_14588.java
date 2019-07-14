@Override protected String getBriefDescription(Project project){
  return "Split column " + _columnName + ("separator".equals(_mode) ? " by separator" : " by field lengths");
}
