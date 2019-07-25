public double[] evaluate(Neighbor[] neighbors){
  double[] scores=new double[neighbors.length];
  for (int i=0; i < neighbors.length; i++)   scores[i]=evaluate(neighbors[i]);
  return scores;
}
