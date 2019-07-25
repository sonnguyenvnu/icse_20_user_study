private void update(){
  if (!entry.isPresent() || layout == null) {
    return;
  }
  ExporterFactory.entryNumber=1;
  BackgroundTask.wrap(() -> layout.generatePreview(entry.get(),database.getDatabase())).onRunning(() -> setPreviewText("<i>" + Localization.lang("Processing %0",Localization.lang("Citation Style")) + ": " + layout.getName() + " ..." + "</i>")).onSuccess(this::setPreviewText).onFailure(exception -> {
    LOGGER.error("Error while generating citation style",exception);
    setPreviewText(Localization.lang("Error while generating citation style"));
  }
).executeWith(taskExecutor);
}
