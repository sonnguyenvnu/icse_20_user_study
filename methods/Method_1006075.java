@Override public void action(){
  panel.output(Localization.lang("Unabbreviating..."));
  BackgroundTask.wrap(this::unabbreviate).onSuccess(panel::output).executeWith(Globals.TASK_EXECUTOR);
}
