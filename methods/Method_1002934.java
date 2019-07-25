@Override public void start(){
  if (!aai.iteratorForAppenders().hasNext()) {
    addWarn("No appender was attached to " + getClass().getSimpleName() + '.');
  }
  if (exporter == null) {
    exporter=builder.build();
  }
  super.start();
}
