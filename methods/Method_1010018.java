/** 
 * Updates visibility of the action presentation in various actions list. Visible only for  {@link IgnoreFile} context.
 * @param e action event
 */
@Override public void update(@NotNull AnActionEvent e){
  final PsiFile file=e.getData(CommonDataKeys.PSI_FILE);
  if (!(file instanceof IgnoreFile)) {
    e.getPresentation().setVisible(false);
    return;
  }
  getTemplatePresentation().setIcon(file.getFileType().getIcon());
}
