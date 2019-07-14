@Bean @ConditionalOnMissingBean public UiController homeUiController() throws IOException {
  List<String> extensionRoutes=new UiRoutesScanner(this.applicationContext).scan(this.adminUi.getExtensionResourceLocations());
  List<String> routes=Stream.concat(DEFAULT_UI_ROUTES.stream(),extensionRoutes.stream()).collect(Collectors.toList());
  Settings uiSettings=Settings.builder().brand(this.adminUi.getBrand()).title(this.adminUi.getTitle()).favicon(this.adminUi.getFavicon()).faviconDanger(this.adminUi.getFaviconDanger()).notificationFilterEnabled(!this.applicationContext.getBeansOfType(NotificationFilterController.class).isEmpty()).routes(routes).externalViews(this.adminUi.getExternalViews()).build();
  String publicUrl=this.adminUi.getPublicUrl() != null ? this.adminUi.getPublicUrl() : this.adminServer.getContextPath();
  return new UiController(publicUrl,this.uiExtensions(),uiSettings);
}
