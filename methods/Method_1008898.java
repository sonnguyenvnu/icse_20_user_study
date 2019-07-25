public static boolean encountered(OpenDoPEIntegrity odIntegrityInstance,String elementName,String id){
  boolean previouslyEncountered=false;
  if (elementName.equals("commentRangeStart")) {
    previouslyEncountered=odIntegrityInstance.commentRangeStart.containsKey(id);
    if (!previouslyEncountered) {
      odIntegrityInstance.commentRangeStart.put(id,id);
    }
    return previouslyEncountered;
  }
  if (elementName.equals("commentRangeEnd")) {
    previouslyEncountered=odIntegrityInstance.commentRangeEnd.containsKey(id);
    if (!previouslyEncountered) {
      odIntegrityInstance.commentRangeEnd.put(id,id);
    }
    return previouslyEncountered;
  }
  if (elementName.equals("commentReference")) {
    previouslyEncountered=odIntegrityInstance.commentReference.containsKey(id);
    if (!previouslyEncountered) {
      odIntegrityInstance.commentReference.put(id,id);
    }
    return previouslyEncountered;
  }
  if (elementName.equals("footnoteReference")) {
    previouslyEncountered=odIntegrityInstance.footnoteReference.containsKey(id);
    if (!previouslyEncountered) {
      odIntegrityInstance.footnoteReference.put(id,id);
    }
    return previouslyEncountered;
  }
  if (elementName.equals("endnoteReference")) {
    previouslyEncountered=odIntegrityInstance.endnoteReference.containsKey(id);
    if (!previouslyEncountered) {
      odIntegrityInstance.endnoteReference.put(id,id);
    }
    return previouslyEncountered;
  }
  log.error("Unexpected elementName: " + elementName);
  return false;
}
