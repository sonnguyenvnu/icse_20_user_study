private void _XXXXX_(Event wrapper) throws Exception {
  MapAttemptFinished js=((MapAttemptFinished)wrapper.getEvent());
  Map<Keys,String> values=new HashMap<>();
  if (js.getTaskid() != null) {
    values.put(Keys.TASKID,js.getTaskid().toString());
  }
  if (js.getTaskType() != null) {
    values.put(Keys.TASK_TYPE,js.getTaskType().toString());
  }
  if (js.getTaskStatus() != null) {
    values.put(Keys.TASK_STATUS,js.getTaskStatus().toString());
  }
  if (js.getAttemptId() != null) {
    values.put(Keys.TASK_ATTEMPT_ID,js.getAttemptId().toString());
  }
  if (js.getFinishTime() != null) {
    values.put(Keys.FINISH_TIME,js.getFinishTime().toString());
  }
  if (js.getMapFinishTime() != null) {
    values.put(Keys.MAP_FINISH_TIME,js.getMapFinishTime().toString());
  }
  if (js.getHostname() != null) {
    values.put(Keys.HOSTNAME,js.getHostname().toString());
  }
  if (js.getPort() != null) {
    values.put(Keys.PORT,js.getPort().toString());
  }
  if (js.getRackname() != null) {
    values.put(Keys.RACK_NAME,js.getRackname().toString());
  }
  if (js.getState() != null) {
    values.put(Keys.STATE_STRING,js.getState().toString());
  }
  ensureRackAfterAttemptFinish(js.getRackname().toString(),values);
  handleTask(RecordTypes.MapAttempt,wrapper.getType(),values,js.getCounters());
}