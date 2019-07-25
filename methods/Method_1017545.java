@Override public void write(StateMachineContext<S,E> context,String contextObj) throws Exception {
  repository.save(context,contextObj);
}
