public void setError(List<Exception> exceptions){
synchronized (config) {
    JSONUtilities.safePut(config,"errors",DefaultImportingController.convertErrorsToJsonArray(exceptions));
    setState("error");
  }
}
