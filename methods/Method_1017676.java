@Override public void invoke(@NotNull final Project project,final Editor editor,final PsiFile file) throws IncorrectOperationException {
  ApplicationManager.getApplication().invokeLater(new Runnable(){
    @Override public void run(){
      changelinkRef(project,editor,linkRefElement,newLinkRef);
    }
  }
);
}
