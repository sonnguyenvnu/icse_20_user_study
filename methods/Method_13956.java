public void scrutinize(Snak snak,EntityIdValue entityId,SnakPosition position,boolean added){
  if (!positionAllowed(snak.getPropertyId(),position)) {
    String positionStr=position.toString().toLowerCase();
    QAWarning issue=new QAWarning("property-found-in-" + positionStr,snak.getPropertyId().getId(),QAWarning.Severity.IMPORTANT,1);
    issue.setProperty("property_entity",snak.getPropertyId());
    addIssue(issue);
  }
}
