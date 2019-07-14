private void printWarnings(Results results){
  for (  Warning warning : results.getWarnings()) {
    if ("00000".equals(warning.getState())) {
      LOG.info("DB: " + warning.getMessage());
    }
 else {
      LOG.warn("DB: " + warning.getMessage() + " (SQL State: " + warning.getState() + " - Error Code: " + warning.getCode() + ")");
    }
  }
}
