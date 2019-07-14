/** 
 * Whether the pager can page backward, meaning the previous month is enabled.
 * @return true if there is a previous month that can be shown
 */
public boolean canGoBack(){
  return pager.getCurrentItem() > 0;
}
