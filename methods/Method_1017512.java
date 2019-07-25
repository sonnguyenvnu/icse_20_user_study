@ReadOperation public List<StateMachineTrace> invoke(){
  return this.repository.findAll();
}
