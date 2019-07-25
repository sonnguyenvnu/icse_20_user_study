/** 
 * Changes parent split orientation if this pane is located within a split.
 */
public void rotate(){
  final Container parent=tabbedPane.getParent();
  if (parent instanceof WebSplitPane) {
    final SplitData<T> splitData=WebDocumentPane.getData((WebSplitPane)parent);
    splitData.changeOrientation();
  }
}
