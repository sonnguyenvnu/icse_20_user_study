private static void evaluate(Options options) throws Exception {
  if (options.goldFile.equals("") || options.predFile.equals(""))   Options.showHelp();
 else {
    Evaluator.evaluate(options.goldFile,options.predFile,options.punctuations);
  }
}
