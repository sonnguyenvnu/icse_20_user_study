/** 
 * Refreshes the editor. This currently just involves checking if the resource has been marked read-only by  {@link EditorUtil#setReadOnly(org.eclipse.core.resources.IResource,boolean)}. If it is, the editor is set to be read only. If it isn't, the editor is set to be editable.
 */
private void refresh(){
  if (getSourceViewer() != null) {
    if (getEditorInput() instanceof IFileEditorInput) {
      final IFileEditorInput fileEditorInput=(IFileEditorInput)getEditorInput();
      getSourceViewer().setEditable(!EditorUtil.isReadOnly(fileEditorInput.getFile()));
    }
 else {
      getSourceViewer().setEditable(false);
    }
  }
}
