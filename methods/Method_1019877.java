@POST public Response create(@Valid ScheduleType scheduleType){
  try {
    log.debug("Creating Role with information: {}",scheduleType);
    return this.getHandlerFactory().getHandler(Handler.SCHEDULE_TYPE_SERVICE).createRecord(scheduleType);
  }
 catch (  Exception e) {
    log.error("Error while creating schedule type service =>",e);
    return this.getExceptionHandler().handle(e);
  }
}
