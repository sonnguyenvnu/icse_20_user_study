public synchronized void startRecording(RecordSpec spec){
  if (state.getStatus() == RecordingStatus.Recording) {
    return;
  }
  if (spec.getTargetBaseUrl() == null || spec.getTargetBaseUrl().isEmpty()) {
    throw new InvalidInputException(Errors.validation("/targetBaseUrl","targetBaseUrl is required"));
  }
  StubMapping proxyMapping=proxyAllTo(spec.getTargetBaseUrl()).build();
  admin.addStubMapping(proxyMapping);
  List<ServeEvent> serveEvents=admin.getServeEvents().getServeEvents();
  UUID initialId=serveEvents.isEmpty() ? null : serveEvents.get(0).getId();
  state=state.start(initialId,proxyMapping,spec);
  notifier().info("Started recording with record spec:\n" + Json.write(spec));
}
