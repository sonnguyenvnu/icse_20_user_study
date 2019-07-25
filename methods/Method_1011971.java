private void check(@NotNull ModelFactory modelFactory){
  if (isLegacy(modelFactory)) {
    String message="The model factory '" + modelFactory + "' seems to be restrained to the legacy API.\n" + "Please reimplement new methods properly since the legacy API is already dropped.";
    if (ApplicationManager.getApplication().isHeadlessEnvironment()) {
      LOG.error(message,new Throwable());
    }
 else {
      Messages.showErrorDialog((Project)null,message,"Attention");
    }
  }
}
