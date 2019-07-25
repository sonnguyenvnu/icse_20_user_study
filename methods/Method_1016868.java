public void train(double numIterations){
  try {
    setNumIterations((int)Math.floor(numIterations));
    estimate();
  }
 catch (  Exception e) {
  }
}
