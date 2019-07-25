void stamp(){
  xformMatrix=new double[coilCount][coilCount];
  int i;
  for (i=0; i != coilCount; i++)   xformMatrix[i][i]=coilInductances[i];
  int j;
  for (i=0; i != coilCount; i++)   for (j=0; j != i; j++)   xformMatrix[i][j]=xformMatrix[j][i]=couplingCoef * Math.sqrt(coilInductances[i] * coilInductances[j]) * coilPolarities[i] * coilPolarities[j];
  CirSim.invertMatrix(xformMatrix,coilCount);
  double ts=isTrapezoidal() ? sim.timeStep / 2 : sim.timeStep;
  for (i=0; i != coilCount; i++)   for (j=0; j != coilCount; j++) {
    xformMatrix[i][j]*=ts;
    int ni=coilNodes[i];
    int nj=coilNodes[j];
    if (i == j)     sim.stampConductance(nodes[ni],nodes[ni + 1],xformMatrix[i][i]);
 else     sim.stampVCCurrentSource(nodes[ni],nodes[ni + 1],nodes[nj],nodes[nj + 1],xformMatrix[i][j]);
  }
  for (i=0; i != nodeCount; i++)   sim.stampRightSide(nodes[i]);
}
