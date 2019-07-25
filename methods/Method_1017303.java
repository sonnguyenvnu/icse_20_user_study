public void update(AnActionEvent event){
  Project project=event.getData(PlatformDataKeys.PROJECT);
  if (project == null || !Symfony2ProjectComponent.isEnabled(project)) {
    this.setStatus(event,false);
    return;
  }
  PsiFile psiFile=event.getData(PlatformDataKeys.PSI_FILE);
  if (!(psiFile instanceof TwigFile)) {
    this.setStatus(event,false);
    return;
  }
  Editor editor=event.getData(PlatformDataKeys.EDITOR);
  if (editor == null) {
    this.setStatus(event,false);
    return;
  }
  PsiElement psiElement;
  if (editor.getSelectionModel().hasSelection()) {
    psiElement=psiFile.findElementAt(editor.getSelectionModel().getSelectionStart());
  }
 else {
    psiElement=psiFile.findElementAt(editor.getCaretModel().getOffset());
  }
  if (psiElement == null) {
    this.setStatus(event,false);
    return;
  }
  IElementType elementType=psiElement.getNode().getElementType();
  if (elementType == XmlTokenType.XML_DATA_CHARACTERS || elementType == XmlTokenType.XML_ATTRIBUTE_VALUE_TOKEN) {
    this.setStatus(event,true);
  }
 else {
    this.setStatus(event,false);
  }
}
