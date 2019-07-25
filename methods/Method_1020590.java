public void start(OkReplayConfig configuration,Tape tape){
  this.configuration=configuration;
  this.tape=Optional.fromNullable(tape);
  isRunning=true;
}
