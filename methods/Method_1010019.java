/** 
 * Presents a list of suitable Gitignore files that can cover currently selected  {@link VirtualFile}. Shows a subgroup with available files or one option if only one Gitignore file is available.
 * @param e action event
 */
@Override public void update(@NotNull AnActionEvent e){
  final VirtualFile file=e.getData(CommonDataKeys.VIRTUAL_FILE);
  final Project project=e.getData(CommonDataKeys.PROJECT);
  final Presentation presentation=e.getPresentation();
  files.clear();
  if (project != null && file != null) {
    try {
      presentation.setVisible(true);
      baseDir=Utils.getModuleRootForFile(file,project);
      for (      IgnoreLanguage language : IgnoreBundle.LANGUAGES) {
        final IgnoreFileType fileType=language.getFileType();
        List<VirtualFile> list=Utils.getSuitableIgnoreFiles(project,fileType,file);
        Collections.reverse(list);
        files.put(fileType,list);
      }
    }
 catch (    ExternalFileException e1) {
      presentation.setVisible(false);
    }
  }
  setPopup(countFiles() > 1);
}
