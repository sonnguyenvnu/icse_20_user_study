public double[] summary(){
  summary[0]=n;
  summary[1]=mean;
  summary[2]=variance;
  summary[3]=stdev;
  summary[4]=Math.sqrt(variance / n);
  summary[5]=mean / summary[4];
  return (summary);
}
