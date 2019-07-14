/** 
 * sun.swing.CachedPainter holds on OpenFile for a while even after JTabbedPane.remove(component)
 */
public void close(){
  linkProvider=null;
  type=null;
  invalidateContent();
  clearLinksCache();
}
