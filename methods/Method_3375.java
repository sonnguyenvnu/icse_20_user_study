@Override public double[] evaluate(String developFile,LinearModel model) throws IOException {
  PerceptronSegmenter segmenter=new PerceptronSegmenter(model);
  double[] prf=Utility.prf(Utility.evaluateCWS(developFile,segmenter));
  return prf;
}
