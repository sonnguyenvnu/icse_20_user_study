private List<UiExtension> uiExtensions() throws IOException {
  UiExtensionsScanner scanner=new UiExtensionsScanner(this.applicationContext);
  List<UiExtension> uiExtensions=scanner.scan(this.adminUi.getExtensionResourceLocations());
  uiExtensions.forEach(e -> log.info("Loaded Spring Boot Admin UI Extension: {}",e));
  return uiExtensions;
}
