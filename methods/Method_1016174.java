/** 
 * Initializes root and active pane.
 */
protected void init(){
  final PaneData rootPane=new PaneData<T>(this);
  add(rootPane.getTabbedPane(),BorderLayout.CENTER);
  root=rootPane;
  activePane=rootPane;
}
