/** 
 * Whether the pager can page forward, meaning the future month is enabled.
 * @return true if there is a future month that can be shown
 */
public boolean canGoForward(){
  return pager.getCurrentItem() < (adapter.getCount() - 1);
}
