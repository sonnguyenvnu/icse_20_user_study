protected void perform(){
  turnRight_routine();
  while (!(isMark())) {
    traceStep_routine();
  }
}
