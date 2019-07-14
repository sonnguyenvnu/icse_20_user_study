public static ScannerSupplier loadPlugins(ScannerSupplier scannerSupplier,Context context){
  JavaFileManager fileManager=context.get(JavaFileManager.class);
  if (!fileManager.hasLocation(StandardLocation.ANNOTATION_PROCESSOR_PATH)) {
    return scannerSupplier;
  }
  JavacProcessingEnvironment processingEnvironment=JavacProcessingEnvironment.instance(context);
  ClassLoader loader=processingEnvironment.getProcessorClassLoader();
  Iterable<BugChecker> extraBugCheckers=ServiceLoader.load(BugChecker.class,loader);
  if (Iterables.isEmpty(extraBugCheckers)) {
    return scannerSupplier;
  }
  return scannerSupplier.plus(ScannerSupplier.fromBugCheckerClasses(Iterables.transform(extraBugCheckers,BugChecker::getClass)));
}
