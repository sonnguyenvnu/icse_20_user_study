private static int process(String[] args) throws FileNotFoundException {
  Iterator<String> arguments=Arrays.asList(args).iterator();
  String mainManifest=null;
  StdLogger.Level logLevel=StdLogger.Level.ERROR;
  ILogger logger=new StdLogger(logLevel);
  while (arguments.hasNext()) {
    String selector=arguments.next();
    if (!selector.startsWith("--")) {
      logger.error(null,"Invalid parameter " + selector + ", expected a command switch");
      return 1;
    }
    if ("--usage".equals(selector)) {
      usage();
      return 0;
    }
    if (!arguments.hasNext()) {
      logger.error(null,"Command switch " + selector + " has no value associated");
      return 1;
    }
    String value=arguments.next();
    if ("--main".equals(selector)) {
      mainManifest=value;
    }
    if ("--log".equals(selector)) {
      logLevel=StdLogger.Level.valueOf(value);
    }
  }
  if (mainManifest == null) {
    System.err.println("--main command switch not provided.");
    return 1;
  }
  logger=createLogger(logLevel);
  File mainManifestFile=checkPath(mainManifest);
  ManifestMerger2.Invoker invoker=createInvoker(mainManifestFile,logger);
  invoker.withFeatures(ManifestMerger2.Invoker.Feature.NO_PLACEHOLDER_REPLACEMENT);
  arguments=Arrays.asList(args).iterator();
  File outFile=null;
  while (arguments.hasNext()) {
    String selector=arguments.next();
    String value=arguments.next();
    if (Strings.isNullOrEmpty(value)) {
      logger.error(null,"Empty value for switch " + selector);
      return 1;
    }
    if ("--libs".equals(selector)) {
      StringTokenizer stringTokenizer=new StringTokenizer(value,File.pathSeparator);
      while (stringTokenizer.hasMoreElements()) {
        File library=checkPath(stringTokenizer.nextToken());
        invoker.addLibraryManifest(library);
      }
    }
    if ("--overlays".equals(selector)) {
      StringTokenizer stringTokenizer=new StringTokenizer(value,File.pathSeparator);
      while (stringTokenizer.hasMoreElements()) {
        File library=checkPath(stringTokenizer.nextToken());
        invoker.addFlavorAndBuildTypeManifest(library);
      }
    }
    if ("--property".equals(selector)) {
      if (!value.contains("=")) {
        logger.error(null,"Invalid property setting, should be NAME=VALUE format");
        return 1;
      }
      try {
        ManifestSystemProperty manifestSystemProperty=ManifestSystemProperty.valueOf(value.substring(0,value.indexOf('=')).toUpperCase(Locale.ENGLISH));
        invoker.setOverride(manifestSystemProperty,value.substring(value.indexOf('=') + 1));
      }
 catch (      IllegalArgumentException e) {
        logger.error(e,"Invalid property name " + value.substring(0,value.indexOf('=')) + ", allowed properties are : " + Joiner.on(',').join(ManifestSystemProperty.values()));
        return 1;
      }
    }
    if ("--placeholder".equals(selector)) {
      if (!value.contains("=")) {
        logger.error(null,"Invalid placeholder setting, should be NAME=VALUE format");
        return 1;
      }
      invoker.setPlaceHolderValue(value.substring(0,value.indexOf('=')),value.substring(value.indexOf('=') + 1));
    }
    if ("--debuggable".equals(selector)) {
      if (value.equals("true")) {
        invoker.withFeatures(ManifestMerger2.Invoker.Feature.DEBUGGABLE);
      }
    }
    if ("--out".equals(selector)) {
      outFile=new File(value);
    }
  }
  try {
    MergingReport merge=invoker.merge();
    if (merge.getResult().isSuccess()) {
      String mergedDocument=merge.getMergedDocument(MergingReport.MergedManifestKind.MERGED);
      if (mergedDocument != null) {
        if (outFile != null) {
          try {
            Files.write(mergedDocument,outFile,Charsets.UTF_8);
          }
 catch (          IOException e) {
            throw new RuntimeException(e);
          }
        }
 else {
          System.out.println(mergedDocument);
        }
      }
    }
 else {
      for (      MergingReport.Record record : merge.getLoggingRecords()) {
        System.err.println(record);
      }
      return 1;
    }
  }
 catch (  ManifestMerger2.MergeFailureException e) {
    logger.error(e,"Exception while merging manifests");
    return 1;
  }
  return 0;
}
