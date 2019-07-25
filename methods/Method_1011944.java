/** 
 * Expects EDT and model read access
 */
void attach(@NotNull final CurrentLinePainter painter,@NotNull final EditorComponent editorComponent){
  ApplicationManager.getApplication().assertIsDispatchThread();
  final SRepository repo=editorComponent.getEditorContext().getRepository();
  SNode node=(painter.getSNode() == null ? null : painter.getSNode().resolve(repo));
  if (node != null && EditorComponentUtil.isNodeShownInTheComponent(editorComponent,node)) {
    editorComponent.addAdditionalPainter(painter);
    editorComponent.repaintExternalComponent();
  }
}
