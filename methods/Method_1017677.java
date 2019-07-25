@Override public void invoke(@NotNull final Project project,final Editor editor,final PsiFile psiFile) throws IncorrectOperationException {
  ApplicationManager.getApplication().invokeLater(new Runnable(){
    @Override public void run(){
      createWikiFile(project,editor,psiFile,filePath);
    }
  }
);
}
