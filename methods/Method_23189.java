/** 
 * Set this sketch to communicate its state back to the PDE. <p/> This uses the stderr stream to write positions of the window (so that it will be saved by the PDE for the next run) and notify on quit. See more notes in the Worker class.
 */
@Override public void setupExternalMessages(){
  frame.addComponentListener(new ComponentAdapter(){
    @Override public void componentMoved(    ComponentEvent e){
      Point where=((Frame)e.getSource()).getLocation();
      sketch.frameMoved(where.x,where.y);
    }
  }
);
}
