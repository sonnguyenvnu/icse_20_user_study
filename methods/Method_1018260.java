@Override public void navigate(MouseEvent mouseEvent,final PsiElement psiElement){
  PsiMethod method=(PsiMethod)psiElement;
  AnActionEvent anActionEvent=AnActionEvent.createFromInputEvent(mouseEvent,"",new Presentation(),new DataContext(){
    @Nullable @Override public Object getData(    String key){
      if (CommonDataKeys.PROJECT.getName().equals(key)) {
        return psiElement.getProject();
      }
 else       if (CommonDataKeys.PSI_ELEMENT.getName().equals(key)) {
        return psiElement;
      }
      return null;
    }
  }
);
  new FindNativeMethodAction(method).actionPerformed(anActionEvent);
}
