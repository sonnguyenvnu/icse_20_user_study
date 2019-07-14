@Override protected String getBriefDescription(Project project){
  return "Copy recon judgments from column " + _fromColumnName + " to " + StringUtils.join(_toColumnNames);
}
