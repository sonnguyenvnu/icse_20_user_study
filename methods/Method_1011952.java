@Override public boolean submit(@NotNull IdeaLoggingEvent[] events,@Nullable String additionalInfo,@NotNull Component parentComponent,@NotNull Consumer<SubmittedReportInfo> consumer){
  ThreadUtils.assertEDT();
  if (events.length == 0) {
    consumer.consume(new SubmittedReportInfo(null,null,SubmissionStatus.FAILED));
    return false;
  }
  final DataContext dataContext=DataManager.getInstance().getDataContext(parentComponent);
  final Project project=PlatformDataKeys.PROJECT.getData(dataContext);
  BlameDialog blameDialog=BlameDialogComponent.getInstance().createDialog(project,parentComponent);
  blameDialog.addExceptions(Arrays.stream(events).map(IdeaLoggingEvent::getThrowable).collect(Collectors.toList()));
  blameDialog.setIssueTitle(extractMessage(events[0]));
  blameDialog.setDescription(additionalInfo);
  blameDialog.setPluginDescriptor(getPluginDescriptor());
  blameDialog.initDialog();
  blameDialog.show();
  if (blameDialog.isCancelled()) {
    consumer.consume(new SubmittedReportInfo(null,"Cancelled issue submit",SubmissionStatus.FAILED));
    return false;
  }
  Response response=blameDialog.getResult();
  assert response != null : "Response must not be null";
  assert response.isSuccess() : "Response is not 'cancelled' or 'success'";
  consumer.consume(new SubmittedReportInfo(null,"",SubmissionStatus.NEW_ISSUE));
  return true;
}
