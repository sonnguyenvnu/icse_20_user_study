/** 
 * Initializes manager if it wasn't already initialized.
 */
public static synchronized void initialize(){
  if (!initialized) {
    initialized=true;
    viewHandlers=new HashMap<DataFlavor,DragViewHandler>();
    final DragSourceAdapter dsa=new DragSourceAdapter(){
      @Override public void dragEnter(      final DragSourceDragEvent dsde){
        actualDragEnter(dsde);
      }
      protected void actualDragEnter(      final DragSourceDragEvent dsde){
        final DragSourceContext dsc=dsde.getDragSourceContext();
        dropLocation=dsc.getComponent();
        final Transferable transferable=dsc.getTransferable();
        final DataFlavor[] flavors=transferable.getTransferDataFlavors();
        for (        final DataFlavor flavor : flavors) {
          if (viewHandlers.containsKey(flavor)) {
            try {
              data=transferable.getTransferData(flavor);
              dragViewHandler=viewHandlers.get(flavor);
              view=dragViewHandler.getView(data,dsde);
              glassPane=GlassPaneManager.getGlassPane(dsc.getComponent());
              glassPane.setPaintedImage(view,getLocation(glassPane,dsde));
              break;
            }
 catch (            final Throwable e) {
              Log.error(DragManager.class,e);
            }
          }
        }
      }
      @Override public void dragMouseMoved(      final DragSourceDragEvent dsde){
        final DragSourceContext dsc=dsde.getDragSourceContext();
        if (dsc.getComponent() != dropLocation) {
          actualDragEnter(dsde);
        }
        if (view != null) {
          final WebGlassPane gp=GlassPaneManager.getGlassPane(dsde.getDragSourceContext().getComponent());
          if (gp != glassPane) {
            glassPane.clearPaintedImage();
            glassPane=gp;
          }
          glassPane.setPaintedImage(view,getLocation(glassPane,dsde));
        }
      }
      public Point getLocation(      final WebGlassPane gp,      final DragSourceDragEvent dsde){
        final Point mp=SwingUtils.getMousePoint(gp);
        final Point vp=dragViewHandler.getViewRelativeLocation(data,dsde);
        return new Point(mp.x + vp.x,mp.y + vp.y);
      }
      @Override public void dragDropEnd(      final DragSourceDropEvent dsde){
        dropLocation=null;
        if (view != null) {
          dragViewHandler.dragEnded(data,dsde);
          glassPane.clearPaintedImage();
          glassPane=null;
          data=null;
          view=null;
          dragViewHandler=null;
        }
      }
    }
;
    DragSource.getDefaultDragSource().addDragSourceListener(dsa);
    DragSource.getDefaultDragSource().addDragSourceMotionListener(dsa);
  }
}
