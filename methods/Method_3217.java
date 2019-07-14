protected void setConfig(String[] args,Config config){
  int i;
  if ((i=argPos("-size",args)) >= 0)   config.setLayer1Size(Integer.parseInt(args[i + 1]));
  if ((i=argPos("-output",args)) >= 0)   config.setOutputFile(args[i + 1]);
  if ((i=argPos("-cbow",args)) >= 0)   config.setUseContinuousBagOfWords(Integer.parseInt(args[i + 1]) == 1);
  if (config.useContinuousBagOfWords())   config.setAlpha(0.05f);
  if ((i=argPos("-alpha",args)) >= 0)   config.setAlpha(Float.parseFloat(args[i + 1]));
  if ((i=argPos("-window",args)) >= 0)   config.setWindow(Integer.parseInt(args[i + 1]));
  if ((i=argPos("-sample",args)) >= 0)   config.setSample(Float.parseFloat(args[i + 1]));
  if ((i=argPos("-hs",args)) >= 0)   config.setUseHierarchicalSoftmax(Integer.parseInt(args[i + 1]) == 1);
  if ((i=argPos("-negative",args)) >= 0)   config.setNegative(Integer.parseInt(args[i + 1]));
  if ((i=argPos("-threads",args)) >= 0)   config.setNumThreads(Integer.parseInt(args[i + 1]));
  if ((i=argPos("-iter",args)) >= 0)   config.setIter(Integer.parseInt(args[i + 1]));
  if ((i=argPos("-min-count",args)) >= 0)   config.setMinCount(Integer.parseInt(args[i + 1]));
}
