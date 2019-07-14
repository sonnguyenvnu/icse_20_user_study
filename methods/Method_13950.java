@Override public void scrutinize(Snak snak,EntityIdValue entityId,boolean added){
  if (StringValue.class.isInstance(snak.getValue())) {
    String value=((StringValue)snak.getValue()).getString();
    PropertyIdValue pid=snak.getPropertyId();
    Pattern pattern=getPattern(pid);
    if (pattern == null) {
      return;
    }
    if (!pattern.matcher(value).matches()) {
      if (added) {
        QAWarning issue=new QAWarning(type,pid.getId(),QAWarning.Severity.IMPORTANT,1);
        issue.setProperty("property_entity",pid);
        issue.setProperty("regex",pattern.toString());
        issue.setProperty("example_value",value);
        issue.setProperty("example_item_entity",entityId);
        addIssue(issue);
      }
 else {
        info("remove-statements-with-invalid-format");
      }
    }
  }
}
