public static Options processArgs(String[] args) throws Exception {
  Options options=new Options();
  for (int i=0; i < args.length; i++) {
    if (args[i].equals("--help") || args[i].equals("-h") || args[i].equals("-help"))     options.showHelp=true;
 else     if (args[i].equals("train"))     options.train=true;
 else     if (args[i].equals("parse_conll"))     options.parseConllFile=true;
 else     if (args[i].equals("parse_partial"))     options.parsePartialConll=true;
 else     if (args[i].equals("eval"))     options.evaluate=true;
 else     if (args[i].equals("parse_tagged"))     options.parseTaggedFile=true;
 else     if (args[i].equals("-train-file") || args[i].equals("-input"))     options.inputFile=args[i + 1];
 else     if (args[i].equals("-punc"))     options.changePunc(args[i + 1]);
 else     if (args[i].equals("-model"))     options.modelFile=args[i + 1];
 else     if (args[i].startsWith("-dev"))     options.devPath=args[i + 1];
 else     if (args[i].equals("-gold"))     options.goldFile=args[i + 1];
 else     if (args[i].startsWith("-parse"))     options.predFile=args[i + 1];
 else     if (args[i].startsWith("-cluster")) {
      options.clusterFile=args[i + 1];
      options.useExtendedWithBrownClusterFeatures=true;
    }
 else     if (args[i].startsWith("-out"))     options.outputFile=args[i + 1];
 else     if (args[i].startsWith("-delim"))     options.separator=args[i + 1];
 else     if (args[i].startsWith("beam:"))     options.beamWidth=Integer.parseInt(args[i].substring(args[i].lastIndexOf(":") + 1));
 else     if (args[i].startsWith("nt:"))     options.numOfThreads=Integer.parseInt(args[i].substring(args[i].lastIndexOf(":") + 1));
 else     if (args[i].startsWith("pt:"))     options.partialTrainingStartingIteration=Integer.parseInt(args[i].substring(args[i].lastIndexOf(":") + 1));
 else     if (args[i].equals("unlabeled"))     options.labeled=Boolean.parseBoolean(args[i]);
 else     if (args[i].equals("lowercase"))     options.lowercase=Boolean.parseBoolean(args[i]);
 else     if (args[i].startsWith("-score"))     options.scorePath=args[i + 1];
 else     if (args[i].equals("basic"))     options.useExtendedFeatures=false;
 else     if (args[i].equals("early"))     options.useMaxViol=false;
 else     if (args[i].equals("static"))     options.useDynamicOracle=false;
 else     if (args[i].equals("random"))     options.useRandomOracleSelection=true;
 else     if (args[i].equals("root_first"))     options.rootFirst=true;
 else     if (args[i].startsWith("iter:"))     options.trainingIter=Integer.parseInt(args[i].substring(args[i].lastIndexOf(":") + 1));
  }
  if (options.train || options.parseTaggedFile || options.parseConllFile)   options.showHelp=false;
  return options;
}
