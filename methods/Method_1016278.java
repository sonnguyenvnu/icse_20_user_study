/** 
 * Centers window relative to screen center.
 * @param window window to process
 * @param < W >    window type
 * @return processed window
 */
public static <W extends Window>W center(final W window){
  window.setLocationRelativeTo(null);
  return window;
}
