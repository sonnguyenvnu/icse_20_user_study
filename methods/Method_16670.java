@Override public void disable(String id){
  Objects.requireNonNull(id);
  int size=createUpdate().set(ScheduleJobEntity.status,DataStatus.STATUS_DISABLED).where(ScheduleJobEntity.id,id).exec();
  if (size > 0) {
    deleteJob(selectByPk(id));
  }
}
