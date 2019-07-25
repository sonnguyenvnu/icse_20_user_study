/** 
 * Updates visibility of the action presentation in various actions list.
 * @param e action event
 */
@Override public void update(@NotNull AnActionEvent e){
  final Project project=e.getData(CommonDataKeys.PROJECT);
  final IdeView view=e.getData(LangDataKeys.IDE_VIEW);
  final PsiDirectory[] directory=view != null ? view.getDirectories() : null;
  if (directory == null || directory.length == 0 || project == null || !this.fileType.getIgnoreLanguage().isNewAllowed()) {
    e.getPresentation().setVisible(false);
  }
}
