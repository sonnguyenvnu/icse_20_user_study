public void reset(){
  for (int i=0; i < classNames.length; i++) {
    this.numInstancesPerClass[i]=r.nextPoisson(classInstanceCountPoissonLamba);
    logger.fine("Class " + classNames[i] + " will have " + numInstancesPerClass[i] + " instances.");
  }
  this.currentClassIndex=classNames.length - 1;
  this.currentInstanceIndex=numInstancesPerClass[currentClassIndex] - 1;
}
