public State addState(Character character){
  State nextState=nextStateIgnoreRootState(character);
  if (nextState == null) {
    nextState=new State(this.depth + 1);
    this.success.put(character,nextState);
  }
  return nextState;
}
