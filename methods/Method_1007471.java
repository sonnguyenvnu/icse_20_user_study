public Frame dup(){
  return new Frame(dupArray(locals),dupArray(stack),stacklen,numMonitorsActive);
}
