private static Supplier<CodeTransformer> scansPlugins(ScannerSupplier scannerSupplier,ErrorProneOptions errorProneOptions,Context context){
  return Suppliers.memoize(() -> {
    try {
      return ErrorProneScannerTransformer.create(ErrorPronePlugins.loadPlugins(scannerSupplier,context).applyOverrides(errorProneOptions).get());
    }
 catch (    InvalidCommandLineOptionException e) {
      throw new PropagatedException(e);
    }
  }
);
}
