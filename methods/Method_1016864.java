public static MarginalProbEstimator read(File f) throws Exception {
  MarginalProbEstimator estimator=null;
  ObjectInputStream ois=new ObjectInputStream(new FileInputStream(f));
  estimator=(MarginalProbEstimator)ois.readObject();
  ois.close();
  return estimator;
}
