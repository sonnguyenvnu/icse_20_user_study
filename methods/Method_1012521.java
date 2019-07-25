public void close(boolean keepChanges){
  if (!keepChanges) {
    setElmValue(currentidx);
  }
 else {
    setElmValue();
  }
  this.hide();
}
