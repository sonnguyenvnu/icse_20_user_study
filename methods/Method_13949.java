@Override public void scrutinize(Snak snak,EntityIdValue entityId,boolean added){
  PropertyIdValue pid=snak.getPropertyId();
  if (!_fetcher.usableOnItems(pid)) {
    QAWarning issue=new QAWarning(type,null,QAWarning.Severity.WARNING,1);
    issue.setProperty("example_entity",entityId);
    addIssue(issue);
  }
}
