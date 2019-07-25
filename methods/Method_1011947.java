void refresh(VirtualFile vf){
  if (false == vf instanceof MPSNodeVirtualFile) {
    return;
  }
  if (Arrays.asList(myFileEditorManagerEx.getOpenFiles()).contains(vf)) {
    myFileEditorManagerEx.updateFilePresentation(vf);
  }
}
