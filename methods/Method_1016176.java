/** 
 * Splits specified pane into two panes using the specified direction to decide split settings.
 * @param splittedPane  pane that will be splitted
 * @param movedDocument document that should be moved from splitted pane to new one
 * @param direction     split direction
 * @return second pane created in the split process
 */
protected PaneData<T> split(final PaneData<T> splittedPane,final T movedDocument,final int direction){
  final PaneData<T> otherPane;
  if (splittedPane != null) {
    final boolean ltr=direction == RIGHT || direction == BOTTOM;
    final int orientation=direction == LEFT || direction == RIGHT ? VERTICAL : HORIZONTAL;
    final SplitData<T> splitData;
    if (splittedPane.getTabbedPane().getParent() == WebDocumentPane.this) {
      otherPane=new PaneData<T>(this);
      final Dimension size=splittedPane.getTabbedPane().getSize();
      final PaneData<T> first=ltr ? splittedPane : otherPane;
      final PaneData<T> last=ltr ? otherPane : splittedPane;
      splitData=new SplitData<T>(WebDocumentPane.this,orientation,first,last);
      remove(splittedPane.getTabbedPane());
      add(splitData.getSplitPane(),BorderLayout.CENTER);
      splitData.getSplitPane().setDividerLocation(orientation == VERTICAL ? size.width / 2 : size.height / 2);
      root=splitData;
    }
 else {
      final WebSplitPane parentSplit=(WebSplitPane)splittedPane.getTabbedPane().getParent();
      final SplitData<T> parentSplitData=getData(parentSplit);
      if (parentSplitData.getOrientation() == orientation && ltr && parentSplitData.getFirst() == splittedPane && parentSplitData.getLast() instanceof PaneData) {
        splitData=parentSplitData;
        otherPane=(PaneData<T>)parentSplitData.getLast();
      }
 else       if (parentSplitData.getOrientation() == orientation && !ltr && parentSplitData.getLast() == splittedPane && parentSplitData.getFirst() instanceof PaneData) {
        splitData=parentSplitData;
        otherPane=(PaneData<T>)parentSplitData.getFirst();
      }
 else {
        otherPane=new PaneData<T>(this);
        final int parentSplitLocation=parentSplitData.getSplitPane().getDividerLocation();
        final Dimension size=splittedPane.getTabbedPane().getSize();
        final PaneData<T> first=ltr ? splittedPane : otherPane;
        final PaneData<T> last=ltr ? otherPane : splittedPane;
        splitData=new SplitData<T>(WebDocumentPane.this,orientation,first,last);
        parentSplitData.replace(splittedPane,splitData);
        splitData.getSplitPane().setDividerLocation(orientation == VERTICAL ? size.width / 2 : size.height / 2);
        parentSplitData.getSplitPane().setDividerLocation(parentSplitLocation);
      }
    }
    if (movedDocument != null) {
      splittedPane.remove(movedDocument);
      otherPane.add(movedDocument);
    }
    revalidate();
    repaint();
    otherPane.getTabbedPane().requestFocusInWindow();
    otherPane.activate();
    fireSplitted(splittedPane,splitData);
  }
 else {
    otherPane=null;
  }
  return otherPane;
}
