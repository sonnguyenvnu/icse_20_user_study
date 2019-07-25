@Activate protected void activate(BundleContext context){
  String os=context.getProperty(Constants.FRAMEWORK_OS_NAME);
  if (os != null && os.toLowerCase().startsWith("macos")) {
    isMac=true;
  }
}
