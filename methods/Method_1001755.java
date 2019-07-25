public boolean apply(){
  if (trace) {
    System.err.println("apply " + this);
  }
  final double movingPointValue=movingPoint.getCurrentValue();
  final double fixedPointValue;
  try {
    fixedPointValue=fixedPoint.getCurrentValue();
  }
 catch (  IllegalStateException e) {
    System.err.println("Pb with force " + this);
    System.err.println("This force has been created here:");
    creationPoint.printStackTrace();
    System.err.println("The fixed point has been created here: " + fixedPoint);
    fixedPoint.printCreationStackTrace();
    throw e;
  }
  final double distance=movingPointValue - fixedPointValue;
  final double diff=distance - minimunDistance;
  if (diff >= 0) {
    if (trace) {
      System.err.println("Not using ");
    }
    return false;
  }
  if (trace) {
    System.err.println("moving " + (-diff) + " " + movingPoint);
  }
  movingPoint.move(-diff);
  return true;
}
