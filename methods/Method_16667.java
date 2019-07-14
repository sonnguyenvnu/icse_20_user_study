protected JobKey createJobKey(ScheduleJobEntity jobEntity){
  String group=jobEntity.getType() == null ? "hsweb.scheduler" : jobEntity.getType();
  return new JobKey(jobEntity.getId(),group);
}
