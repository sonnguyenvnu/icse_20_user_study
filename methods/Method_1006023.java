@Override public Node description(){
  PreviewViewer previewViewer=new PreviewViewer(new BibDatabaseContext(),JabRefGUI.getMainFrame().getDialogService(),Globals.stateManager);
  previewViewer.setEntry(memEntry);
  return previewViewer;
}
