protected final void check(){
  if (myActiveChild != null) {
    myDone=myAfterActiveChild;
    myActiveChild=null;
    setTitleInternal(myName);
    setStepInternal(myStepName);
  }
}
