@Override public void batchIsFinished(){
  for (  Entry<PropertyIdValue,PropertyIdValue> propertyPair : _inverse.entrySet()) {
    PropertyIdValue ourProperty=propertyPair.getKey();
    for (    Entry<EntityIdValue,Set<EntityIdValue>> itemLinks : _statements.get(ourProperty).entrySet()) {
      for (      EntityIdValue idValue : itemLinks.getValue()) {
        PropertyIdValue missingProperty=propertyPair.getValue();
        Set<EntityIdValue> reciprocalLinks=_statements.get(missingProperty).get(idValue);
        if (reciprocalLinks == null || !reciprocalLinks.contains(itemLinks.getKey())) {
          QAWarning issue=new QAWarning(type,ourProperty.getId(),QAWarning.Severity.IMPORTANT,1);
          issue.setProperty("added_property_entity",ourProperty);
          issue.setProperty("inverse_property_entity",missingProperty);
          issue.setProperty("source_entity",itemLinks.getKey());
          issue.setProperty("target_entity",idValue);
          addIssue(issue);
        }
      }
    }
  }
}
