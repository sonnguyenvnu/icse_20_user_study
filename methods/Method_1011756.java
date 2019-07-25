public void dispose(){
synchronized (myDisposedLock) {
    myDisposed=true;
    try {
synchronized (myChangesMessages) {
        SetSequence.fromSet(MapSequence.fromMap(myChangesMessages).keySet()).toListSequence().visitAll(new IVisitor<ModelChange>(){
          public void visit(          ModelChange ch){
            removeMessages(ch);
          }
        }
);
      }
      getHighlightManager().clearForOwner(this);
      if (myStripsPainter != null) {
        getLeftEditorHighlighter().removeFoldingAreaPainter(myStripsPainter);
        myStripsPainter.dispose();
      }
    }
  finally {
      if (myCurrentDifference != null) {
        myCurrentDifference.removeDifferenceListener(myListener);
        myListener=null;
      }
    }
  }
}
