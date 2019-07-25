@Override public NoopQueryLoggingComponent component(PrimaryComponent primary){
  return DaggerNoopQueryLoggingComponent.builder().primaryComponent(primary).build();
}
