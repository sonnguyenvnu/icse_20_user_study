/** 
 * Start tracking all line changes (due to edits) in the current tab. 
 */
protected void startTrackingLineChanges(){
  SketchCode tab=editor.getSketch().getCurrentCode();
  if (runtimeTabsTracked.contains(tab.getFileName())) {
    return;
  }
  for (int i=0; i < tab.getLineCount(); i++) {
    LineID old=new LineID(tab.getFileName(),i);
    LineID tracked=new LineID(tab.getFileName(),i);
    tracked.startTracking(editor.currentDocument());
    runtimeLineChanges.put(old,tracked);
  }
  runtimeTabsTracked.add(tab.getFileName());
}
