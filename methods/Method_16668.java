protected TriggerKey createTriggerKey(ScheduleJobEntity jobEntity){
  String group=jobEntity.getType() == null ? "hsweb.scheduler" : jobEntity.getType();
  return new TriggerKey(jobEntity.getId(),group);
}
