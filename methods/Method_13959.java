@Override public void scrutinize(Snak snak,EntityIdValue entityId,boolean added){
  if (entityId.equals(snak.getValue())) {
    QAWarning issue=new QAWarning(type,null,QAWarning.Severity.WARNING,1);
    issue.setProperty("example_entity",entityId);
    addIssue(issue);
  }
}
