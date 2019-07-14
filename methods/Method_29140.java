public Long getCheckCycleMillionTime(){
  if (InstanceAlertCheckCycleEnum.ONE_MINUTE.getValue() == checkCycle) {
    return TimeUnit.MINUTES.toMillis(1);
  }
 else   if (InstanceAlertCheckCycleEnum.FIVE_MINUTE.getValue() == checkCycle) {
    return TimeUnit.MINUTES.toMillis(5);
  }
 else   if (InstanceAlertCheckCycleEnum.HALF_HOUR.getValue() == checkCycle) {
    return TimeUnit.MINUTES.toMillis(30);
  }
 else   if (InstanceAlertCheckCycleEnum.ONE_HOUR.getValue() == checkCycle) {
    return TimeUnit.MINUTES.toMillis(60);
  }
 else   if (InstanceAlertCheckCycleEnum.ONE_DAY.getValue() == checkCycle) {
    return TimeUnit.DAYS.toMillis(1);
  }
  return null;
}
