private Optional<LintingResponse> lint(final PsiFile file){
  try {
    return Optional.of(zallyService.lint(file.getText()));
  }
 catch (  final Exception e) {
    Notification notification=new Notification("Zally","Could not lint API spec",e.getMessage(),NotificationType.ERROR);
    Notifications.Bus.notify(notification);
    notification.addAction(new NotificationAction("Configure Zally"){
      @Override public void actionPerformed(      @NotNull AnActionEvent anActionEvent,      @NotNull Notification notification){
        final DataContext dataContext=anActionEvent.getDataContext();
        final Project project=PlatformDataKeys.PROJECT.getData(dataContext);
        ShowSettingsUtil.getInstance().showSettingsDialog(project,ZallySettingsGui.class);
      }
    }
);
    return Optional.empty();
  }
}
