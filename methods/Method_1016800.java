private double shift(){
  double[] nextS=null, nextY=null;
  int listSize=s.size();
  if (listSize < m) {
    nextS=new double[parameters.length];
    nextY=new double[parameters.length];
  }
 else {
    nextS=s.removeFirst();
    nextY=y.removeFirst();
    rhos.removeFirst();
  }
  double rho=0.0;
  double yDotY=0.0;
  for (int i=0; i < parameters.length; i++) {
    if (Double.isInfinite(parameters[i]) && Double.isInfinite(oldParameters[i]) && parameters[i] * oldParameters[i] > 0)     nextS[i]=0;
 else     nextS[i]=parameters[i] - oldParameters[i];
    if (Double.isInfinite(grad[i]) && Double.isInfinite(oldGrad[i]) && grad[i] * oldGrad[i] > 0)     nextY[i]=0;
 else     nextY[i]=grad[i] - oldGrad[i];
    rho+=nextS[i] * nextY[i];
    yDotY+=nextY[i] * nextY[i];
  }
  logger.fine("rho=" + rho);
  if (rho < 0) {
    throw new InvalidOptimizableException("rho = " + rho + " < 0: " + "Invalid hessian inverse. " + "Gradient change should be opposite of parameter change.");
  }
  s.addLast(nextS);
  y.addLast(nextY);
  rhos.addLast(rho);
  storeSrcInDest(parameters,oldParameters);
  storeSrcInDest(grad,oldGrad);
  return yDotY;
}
