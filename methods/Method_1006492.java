@Override public Container limit(int maxcardinality){
  if (maxcardinality >= getCardinality()) {
    return clone();
  }
  int r;
  int cardinality=0;
  for (r=0; r < this.nbrruns; ++r) {
    cardinality+=toIntUnsigned(getLength(r)) + 1;
    if (maxcardinality <= cardinality) {
      break;
    }
  }
  RunContainer rc=new RunContainer(Arrays.copyOf(valueslength,2 * (r + 1)),r + 1);
  rc.setLength(r,(short)(toIntUnsigned(rc.getLength(r)) - cardinality + maxcardinality));
  return rc;
}
