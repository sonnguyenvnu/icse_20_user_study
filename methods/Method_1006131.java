@Subscribe public synchronized void listen(@SuppressWarnings("unused") BibDatabaseContextChangedEvent event){
  try {
    executor.submit(() -> {
      eventBus.post(new AutosaveEvent());
    }
);
  }
 catch (  RejectedExecutionException e) {
    LOGGER.debug("Rejecting autosave while another save process is already running.");
  }
}
