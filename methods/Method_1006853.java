protected void stop(String pattern,State restart){
  EndState next=new EndState(FlowExecutionStatus.STOPPED,"STOPPED",prefix + "stop" + (endCounter++),true);
  addTransition(pattern,next);
  currentState=next;
  addTransition("*",restart);
}
