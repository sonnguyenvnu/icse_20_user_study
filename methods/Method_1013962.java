/** 
 * Merges the content of the specified source  {@link DiscoveryResult} into this object.<p> <i>Hint:</i> The  {@link DiscoveryResultFlag} of this object keeps its state.<p> This method returns silently if the specified source  {@link DiscoveryResult} is {@code null} or its {@code Thing}type or ID does not fit to this object.
 * @param sourceResult the discovery result which is used as source for the merge
 */
public void synchronize(DiscoveryResult sourceResult){
  if ((sourceResult != null) && (sourceResult.getThingUID().equals(this.thingUID))) {
    this.properties=sourceResult.getProperties();
    this.representationProperty=sourceResult.getRepresentationProperty();
    this.label=sourceResult.getLabel();
    this.timestamp=new Date().getTime();
    this.timeToLive=sourceResult.getTimeToLive();
  }
}
