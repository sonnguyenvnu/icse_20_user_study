@Override public double value(String name){
  Metrics metrics=Metrics.valueOf(name);
switch (metrics) {
case min:
    return this.min;
case max:
  return this.max;
case avg:
return this.getAvg();
case count:
return this.count;
case sum:
return this.sum;
default :
throw new IllegalArgumentException("Unknown value [" + name + "] in common stats aggregation");
}
}
