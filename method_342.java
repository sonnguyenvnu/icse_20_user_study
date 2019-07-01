public static void _XXXXX_(ServerConfiguration conf) throws BookieException.UpgradeException, InterruptedException {
  LOG.info("Rolling back upgrade...");
  try {
    runFunctionWithRegistrationManager(conf,rm -> {
      try {
        _XXXXX_(conf,rm);
      }
 catch (      UpgradeException e) {
        throw new UncheckedExecutionException(e.getMessage(),e);
      }
      return null;
    }
);
  }
 catch (  MetadataException e) {
    throw new UpgradeException(e);
  }
catch (  ExecutionException e) {
    throw new UpgradeException(e.getCause());
  }
  LOG.info("Done");
}