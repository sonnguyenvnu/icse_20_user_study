public double getValue(CycleView.Prop pathRotate,float x){
  int index=pathRotate.ordinal();
  for (  Cycle cycle : myCycles) {
    if (cycle.myModel.mAttrIndex == index) {
      return cycle.myModel.getComputedValue(x);
    }
  }
  return 0;
}
