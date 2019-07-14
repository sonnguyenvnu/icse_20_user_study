private void addViolation(RuleViolation violation){
  String packageName=violation.getPackageName();
  int index=packageName.indexOf('.',0);
  while (index > -1) {
    String currentPackage=packageName.substring(0,index);
    ReportNode reportNode=reportNodesByPackage.get(currentPackage);
    if (reportNode == null) {
      reportNode=new ReportNode(currentPackage);
      reportNodesByPackage.put(currentPackage,reportNode);
    }
    reportNode.incrementViolations();
    int oldIndex=index;
    index=packageName.indexOf('.',index + 1);
    if (index == -1 && oldIndex != packageName.length()) {
      index=packageName.length();
    }
  }
  String fqClassName=packageName + "." + violation.getClassName();
  ReportNode classNode=reportNodesByPackage.get(fqClassName);
  if (classNode == null) {
    classNode=new ReportNode(packageName,violation.getClassName());
    reportNodesByPackage.put(fqClassName,classNode);
  }
  classNode.addRuleViolation(violation);
  ReportNode rootNode=reportNodesByPackage.get(ReportNode.ROOT_NODE_NAME);
  if (rootNode == null) {
    rootNode=new ReportNode("Aggregate");
    reportNodesByPackage.put(ReportNode.ROOT_NODE_NAME,rootNode);
  }
  rootNode.incrementViolations();
}
