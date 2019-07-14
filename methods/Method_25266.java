public static ErrorProneAnalyzer createByScanningForPlugins(ScannerSupplier scannerSupplier,ErrorProneOptions errorProneOptions,Context context){
  return new ErrorProneAnalyzer(scansPlugins(scannerSupplier,errorProneOptions,context),errorProneOptions,context,JavacErrorDescriptionListener.provider(context));
}
