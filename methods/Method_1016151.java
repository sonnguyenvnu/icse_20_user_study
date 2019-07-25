/** 
 * Starts thumbnail generation.
 */
@Override public void run(){
  if (aborted) {
    cleanup();
    return;
  }
  createThumbnail(element.getFile(),list.isGenerateThumbnails());
  if (aborted) {
    cleanup();
    return;
  }
  SwingUtilities.invokeLater(new Runnable(){
    @Override public void run(){
      list.repaint(element);
    }
  }
);
  if (aborted) {
    cleanup();
    return;
  }
  cleanup();
}
