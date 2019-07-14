private Instance apply(InstanceEvent event,boolean isNewEvent){
  Assert.notNull(event,"'event' must not be null");
  Assert.isTrue(this.id.equals(event.getInstance()),"'event' must refer the same instance");
  Assert.isTrue(event.getVersion() >= this.nextVersion(),() -> "Event " + event.getVersion() + " must be greater or equal to " + this.nextVersion());
  List<InstanceEvent> unsavedEvents=appendToEvents(event,isNewEvent);
  if (event instanceof InstanceRegisteredEvent) {
    Registration registration=((InstanceRegisteredEvent)event).getRegistration();
    return new Instance(this.id,event.getVersion(),registration,true,StatusInfo.ofUnknown(),event.getTimestamp(),Info.empty(),Endpoints.empty(),updateBuildVersion(registration.getMetadata()),updateTags(registration.getMetadata()),unsavedEvents);
  }
 else   if (event instanceof InstanceRegistrationUpdatedEvent) {
    Registration registration=((InstanceRegistrationUpdatedEvent)event).getRegistration();
    return new Instance(this.id,event.getVersion(),registration,this.registered,this.statusInfo,this.statusTimestamp,this.info,this.endpoints,updateBuildVersion(registration.getMetadata(),this.info.getValues()),updateTags(registration.getMetadata(),this.info.getValues()),unsavedEvents);
  }
 else   if (event instanceof InstanceStatusChangedEvent) {
    StatusInfo statusInfo=((InstanceStatusChangedEvent)event).getStatusInfo();
    return new Instance(this.id,event.getVersion(),this.registration,this.registered,statusInfo,event.getTimestamp(),this.info,this.endpoints,this.buildVersion,this.tags,unsavedEvents);
  }
 else   if (event instanceof InstanceEndpointsDetectedEvent) {
    Endpoints endpoints=((InstanceEndpointsDetectedEvent)event).getEndpoints();
    return new Instance(this.id,event.getVersion(),this.registration,this.registered,this.statusInfo,this.statusTimestamp,this.info,endpoints,this.buildVersion,this.tags,unsavedEvents);
  }
 else   if (event instanceof InstanceInfoChangedEvent) {
    Info info=((InstanceInfoChangedEvent)event).getInfo();
    Map<String,?> metaData=this.registration != null ? this.registration.getMetadata() : emptyMap();
    return new Instance(this.id,event.getVersion(),this.registration,this.registered,this.statusInfo,this.statusTimestamp,info,this.endpoints,updateBuildVersion(metaData,info.getValues()),updateTags(metaData,info.getValues()),unsavedEvents);
  }
 else   if (event instanceof InstanceDeregisteredEvent) {
    return new Instance(this.id,event.getVersion(),this.registration,false,StatusInfo.ofUnknown(),event.getTimestamp(),Info.empty(),Endpoints.empty(),null,Tags.empty(),unsavedEvents);
  }
  return this;
}
