public void setDot(float x){
  if (Float.isNaN(x)) {
    for (    Cycle myCycle : myCycles) {
      myCycle.myModel.setDot(x,0);
    }
    return;
  }
  for (  Cycle myCycle : myCycles) {
    float val=myCycle.myModel.getComputedValue(x);
    myCycle.myModel.setDot(x,val);
  }
}
