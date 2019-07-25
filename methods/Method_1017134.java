@Override public Slf4jQueryLoggingComponent component(PrimaryComponent primary){
  return DaggerSlf4jQueryLoggingComponent.builder().primaryComponent(primary).slf4jQueryLoggingModule(this).build();
}
