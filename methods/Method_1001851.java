public ClusterPosition delta(double m1,double m2){
  return new ClusterPosition(minX,minY,maxX + m1,maxY + m2);
}
