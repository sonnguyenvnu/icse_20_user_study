public Options clone(){
  Options options=new Options();
  options.train=train;
  options.labeled=labeled;
  options.trainingIter=trainingIter;
  options.useMaxViol=useMaxViol;
  options.beamWidth=beamWidth;
  options.devPath=devPath;
  options.evaluate=evaluate;
  options.goldFile=goldFile;
  options.inputFile=inputFile;
  options.lowercase=lowercase;
  options.numOfThreads=numOfThreads;
  options.outputFile=outputFile;
  options.useDynamicOracle=useDynamicOracle;
  options.modelFile=modelFile;
  options.rootFirst=rootFirst;
  options.parseConllFile=parseConllFile;
  options.parseTaggedFile=parseTaggedFile;
  options.predFile=predFile;
  options.showHelp=showHelp;
  options.separator=separator;
  options.useExtendedFeatures=useExtendedFeatures;
  options.parsePartialConll=parsePartialConll;
  options.partialTrainingStartingIteration=partialTrainingStartingIteration;
  return options;
}
