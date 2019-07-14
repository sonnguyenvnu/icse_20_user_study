@Primary @Bean(initMethod="start",destroyMethod="stop") public RemindingNotifier remindingNotifier(){
  RemindingNotifier notifier=new RemindingNotifier(filteringNotifier(),this.repository);
  notifier.setReminderPeriod(Duration.ofMinutes(10));
  notifier.setCheckReminderInverval(Duration.ofSeconds(10));
  return notifier;
}
