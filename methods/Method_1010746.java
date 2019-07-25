public static EditorCell_URL create(EditorContext editorContext,SNode node,SProperty property){
  NodeReadAccessInEditorListener listener=NodeReadAccessCasterInEditor.getReadAccessListener();
  ModelAccessor accessor=new PropertyAccessor(node,property,false,true,editorContext);
  if (listener != null) {
    listener.clearCleanlyReadAccessProperties();
  }
  EditorCell_URL result=new EditorCell_URL(editorContext,accessor,node);
  if (listener != null) {
    for (    Pair<SNodeReference,String> pair : listener.popCleanlyReadAccessedProperties()) {
      result.getEditorComponent().getUpdater().getCurrentUpdateSession().registerCleanDependency(result,pair);
    }
  }
  return result;
}
