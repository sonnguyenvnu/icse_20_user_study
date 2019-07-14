private void assignReportLevelsCompat(){
  int methodLevel=getProperty(METHOD_LEVEL_DESCRIPTOR);
  int classLevel=getProperty(CLASS_LEVEL_DESCRIPTOR);
  int commonLevel=getProperty(REPORT_LEVEL_DESCRIPTOR);
  if (methodLevel == METHOD_LEVEL_DESCRIPTOR.defaultValue() && classLevel == CLASS_LEVEL_DESCRIPTOR.defaultValue() && commonLevel != REPORT_LEVEL_DESCRIPTOR.defaultValue()) {
    LOG.warning("Rule CyclomaticComplexity uses deprecated property 'reportLevel'. " + "Future versions of PMD will remove support for this property. " + "Please use 'methodReportLevel' and 'classReportLevel' instead!");
    methodLevel=commonLevel;
    classLevel=commonLevel * 8;
  }
  methodReportLevel=methodLevel;
  classReportLevel=classLevel;
}
