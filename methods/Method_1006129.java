@Override public void action(){
  BackgroundTask.wrap(this::sendEmail).onSuccess(frame.getDialogService()::notify).onFailure(e -> {
    String message=Localization.lang("Error creating email");
    LOGGER.warn(message,e);
    frame.getDialogService().notify(message);
  }
).executeWith(Globals.TASK_EXECUTOR);
}
