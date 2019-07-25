@PostConstruct public void initialize(){
  ScheduleExpression se=new ScheduleExpression();
  se.hour("*").minute("*").second("0/3");
  timerService.createCalendarTimer(se,new TimerConfig("EJB timer service timeout at ",false));
}
