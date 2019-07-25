public void update(AnActionEvent e){
  final Project project=CommonDataKeys.PROJECT.getData(e.getDataContext());
  if (project == null || project.isDisposed() || project.isDefault()) {
    e.getPresentation().setVisible(false);
    return;
  }
  Deployable server=WebDeploymentDataKeys.DEPLOYABLE.getData(e.getDataContext());
  if (server == null || !PublishConfig.getInstance(project).isDefault(server) || !server.needsTransfer() || server.validateFast() != null) {
    e.getPresentation().setVisible(false);
    return;
  }
  e.getPresentation().setVisible(RemoteWebServerUtil.hasConfiguredRemoteFile(project));
}
