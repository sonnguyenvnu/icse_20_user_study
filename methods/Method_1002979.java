@Override public void update(@NotNull AnActionEvent event){
  final Presentation presentation=event.getPresentation();
  final DataContext dataContext=event.getDataContext();
  final Project project=event.getProject();
  if (project == null) {
    presentation.setEnabled(false);
    return;
  }
  final Editor editor=PlatformDataKeys.EDITOR.getData(dataContext);
  if (null != editor) {
    final PsiFile file=PsiUtilBase.getPsiFileInEditor(editor,project);
    presentation.setEnabled(file != null && isValidForFile(editor,file));
    return;
  }
  boolean isValid=false;
  final VirtualFile[] files=PlatformDataKeys.VIRTUAL_FILE_ARRAY.getData(dataContext);
  if (null != files) {
    PsiManager psiManager=PsiManager.getInstance(project);
    for (    VirtualFile file : files) {
      if (file.isDirectory()) {
        isValid=true;
        break;
      }
      if (StdFileTypes.JAVA.equals(file.getFileType())) {
        PsiJavaFile psiFile=(PsiJavaFile)psiManager.findFile(file);
        if (psiFile != null) {
          for (          PsiClass psiClass : psiFile.getClasses()) {
            if (isValidForClass(psiClass)) {
              isValid=true;
              break;
            }
            for (            PsiClass innerClass : psiClass.getAllInnerClasses()) {
              if (isValidForClass(innerClass)) {
                isValid=true;
                break;
              }
            }
          }
        }
      }
      if (isValid) {
        break;
      }
    }
  }
  presentation.setEnabled(isValid);
}
