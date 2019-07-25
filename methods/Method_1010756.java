@Override public void update(){
  final jetbrains.mps.project.Project p=getCurrentProject();
  final Highlighter highlighter=p == null ? null : p.getComponent(Highlighter.class);
  getModelAccess().runReadAction(() -> {
    rebuildAfterReloadModel();
    if (highlighter != null) {
      highlighter.resetCheckedStateInBackground(EditorComponent.this);
    }
    rebuildEditorContent();
  }
);
}
