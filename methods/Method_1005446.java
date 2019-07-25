public static AndroidDevMetrics singleton(){
  if (singleton == null) {
    throw new IllegalStateException("Must Initialize Dagger2Metrics before using singleton()");
  }
 else {
    return singleton;
  }
}
