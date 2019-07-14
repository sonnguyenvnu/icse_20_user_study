@Override public void scrutinize(Statement statement,EntityIdValue entityId,boolean added){
  PropertyIdValue pid=statement.getClaim().getMainSnak().getPropertyId();
  if (_fetcher.hasDistinctValues(pid)) {
    Value mainSnakValue=statement.getClaim().getMainSnak().getValue();
    Map<Value,EntityIdValue> seen=_seenValues.get(pid);
    if (seen == null) {
      seen=new HashMap<Value,EntityIdValue>();
      _seenValues.put(pid,seen);
    }
    if (seen.containsKey(mainSnakValue)) {
      EntityIdValue otherId=seen.get(mainSnakValue);
      QAWarning issue=new QAWarning(type,pid.getId(),QAWarning.Severity.IMPORTANT,1);
      issue.setProperty("property_entity",pid);
      issue.setProperty("item1_entity",entityId);
      issue.setProperty("item2_entity",otherId);
      addIssue(issue);
    }
 else {
      seen.put(mainSnakValue,entityId);
    }
  }
}
