@Override public boolean intersects(int minimum,int supremum){
  if ((minimum < 0) || (supremum < minimum) || (supremum > (1 << 16))) {
    throw new RuntimeException("This should never happen (bug).");
  }
  for (int i=0; i < numberOfRuns(); ++i) {
    short runFirstValue=getValue(i);
    short runLastValue=(short)(runFirstValue + getLength(i));
    if (BufferUtil.toIntUnsigned(runFirstValue) < supremum && BufferUtil.compareUnsigned(runLastValue,(short)minimum) >= 0) {
      return true;
    }
  }
  return false;
}
