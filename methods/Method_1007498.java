public void reset(){
  curState=null;
  pc=0;
  for (int ii=0; ii < stateStack.length; ii++)   stateStack[ii]=null;
  iStack=-1;
  isPausing=false;
  isDone=false;
}
