/** 
 * Quick drag handler installation method.
 * @param paneData pane into which this drag handler should be installed
 */
public static void install(final PaneData paneData){
  final WebTabbedPane tabbedPane=paneData.getTabbedPane();
  final MouseAdapter dragAdapter=new MouseAdapter(){
    @Override public void mousePressed(    final MouseEvent e){
      readyToDrag=paneData.getDocumentPane().isDragEnabled() && SwingUtils.isLeftMouseButton(e);
    }
    @Override public void mouseDragged(    final MouseEvent e){
      if (readyToDrag) {
        readyToDrag=false;
        tabbedPane.getTransferHandler().exportAsDrag(tabbedPane,e,TransferHandler.MOVE);
      }
    }
    @Override public void mouseReleased(    final MouseEvent e){
      readyToDrag=false;
    }
  }
;
  tabbedPane.addMouseListener(dragAdapter);
  tabbedPane.addMouseMotionListener(dragAdapter);
  tabbedPane.setTransferHandler(new DocumentDragHandler(paneData));
}
