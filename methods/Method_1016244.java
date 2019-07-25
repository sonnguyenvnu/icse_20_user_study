/** 
 * {@inheritDoc}
 */
@Override public void install(final E scrollbar){
  super.install(scrollbar);
  animated=true;
  mouseAdapter=new MouseAdapter(){
    @Override public void mousePressed(    final MouseEvent e){
      setPressed(true);
    }
    @Override public void mouseReleased(    final MouseEvent e){
      setPressed(false);
    }
    @Override public void mouseEntered(    final MouseEvent e){
      setRollover(thumbBounds.contains(e.getPoint()));
    }
    @Override public void mouseMoved(    final MouseEvent e){
      setRollover(thumbBounds.contains(e.getPoint()));
    }
    @Override public void mouseExited(    final MouseEvent e){
      setRollover(false);
    }
  }
;
  scrollbar.addMouseListener(mouseAdapter);
  scrollbar.addMouseMotionListener(mouseAdapter);
}
