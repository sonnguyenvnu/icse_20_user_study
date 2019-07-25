public void mark(SNode node,Color color,String messageText,EditorMessageOwner owner){
  if (node == null)   return;
  mark(new DefaultEditorMessage(node,color,messageText,owner));
}
