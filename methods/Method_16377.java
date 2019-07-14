@Override public void run(String... args) throws Exception {
  try {
    dynamicFormService.deployAllFromLog();
  }
 catch (  Exception e) {
    logger.error("deploy form error",e);
  }
}
