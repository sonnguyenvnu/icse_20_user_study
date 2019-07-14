@Override public void scrutinize(Statement statement,EntityIdValue entityId,boolean added){
  PropertyIdValue statementProperty=statement.getClaim().getMainSnak().getPropertyId();
  Set<PropertyIdValue> qualifiers=statement.getClaim().getQualifiers().stream().map(e -> e.getProperty()).collect(Collectors.toSet());
  Set<PropertyIdValue> missingQualifiers=mandatoryQualifiers(statementProperty).stream().filter(p -> !qualifiers.contains(p)).collect(Collectors.toSet());
  Set<PropertyIdValue> disallowedQualifiers=qualifiers.stream().filter(p -> !qualifierIsAllowed(statementProperty,p)).collect(Collectors.toSet());
  for (  PropertyIdValue missing : missingQualifiers) {
    QAWarning issue=new QAWarning(missingMandatoryQualifiersType,statementProperty.getId() + "-" + missing.getId(),QAWarning.Severity.WARNING,1);
    issue.setProperty("statement_property_entity",statementProperty);
    issue.setProperty("missing_property_entity",missing);
    issue.setProperty("example_item_entity",entityId);
    addIssue(issue);
  }
  for (  PropertyIdValue disallowed : disallowedQualifiers) {
    QAWarning issue=new QAWarning(disallowedQualifiersType,statementProperty.getId() + "-" + disallowed.getId(),QAWarning.Severity.WARNING,1);
    issue.setProperty("statement_property_entity",statementProperty);
    issue.setProperty("disallowed_property_entity",disallowed);
    issue.setProperty("example_item_entity",entityId);
    addIssue(issue);
  }
}
