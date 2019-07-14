public JoddJoy withWebApp(final Consumer<WebApp> webAppConsumer){
  joyMadvoc.add(webAppConsumer);
  return this;
}
