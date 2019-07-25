public boolean exists(ProvenanceEventRecordDTO eventRecordDTO){
  return repository.exists(new JpaNifiEvent.NiFiEventPK(eventRecordDTO.getEventId(),eventRecordDTO.getFlowFileUuid()));
}
