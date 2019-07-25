@Override public void execute(State state){
  state.getInequalities().setSolvingInProcess(true);
  myAction.run();
  myAction=null;
}
