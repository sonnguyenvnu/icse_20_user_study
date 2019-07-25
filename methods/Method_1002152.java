@Override public void execution(QuartzJob quartzJob){
  if (quartzJob.getId().equals(1L)) {
    throw new BadRequestException("???????");
  }
  quartzManage.runAJobNow(quartzJob);
}
