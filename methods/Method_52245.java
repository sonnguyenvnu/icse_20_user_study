private int getReportLevel(){
  double oldProp=getProperty(MINIMUM_DESCRIPTOR);
  if (oldProp != MINIMUM_DESCRIPTOR.defaultValue()) {
    LOG.warning("Rule NPathComplexity uses deprecated property 'minimum'. Future versions of PMD will remove support for this property. Please use 'reportLevel' instead!");
    return (int)oldProp;
  }
  return getProperty(REPORT_LEVEL_DESCRIPTOR);
}
