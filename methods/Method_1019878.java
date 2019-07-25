@PUT @Path("/{id}") public Response update(@PathParam("id") long id,@Valid ScheduleType scheduleType){
  try {
    log.debug("Updating repo with information: {}",scheduleType);
    return this.getHandlerFactory().getHandler(Handler.SCHEDULE_TYPE_SERVICE).updateRecord(scheduleType);
  }
 catch (  Exception e) {
    log.error("Error while updating schedule type service =>",e);
    return this.getExceptionHandler().handle(e);
  }
}
