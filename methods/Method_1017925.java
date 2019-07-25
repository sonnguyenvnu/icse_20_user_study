@Override public Object inspect(Configuration configuration){
  List<Inspection> inspections=getInspections();
  for (  Inspection inspection : inspections) {
    InspectionStatus status;
    try {
      status=inspection.inspect(configuration);
    }
 catch (    Exception e) {
      status=new InspectionStatus(false);
      String exceptionMsg=e.getMessage();
      String msg=String.format("An error occurred running inspection '%s': %s",inspection.getName(),exceptionMsg == null ? "No details available" : exceptionMsg);
      status.addError(msg);
      LOG.error(msg,e);
    }
    inspection.setStatus(status);
  }
  try {
    return new ObjectMapper().writeValueAsString(inspections);
  }
 catch (  JsonProcessingException e) {
    throw new IllegalStateException("An error occurred while serialising inspections",e);
  }
}
