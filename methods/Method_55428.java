/** 
 * Sets the specified value to the  {@code cbWndExtra} field. 
 */
public WNDCLASSEX cbWndExtra(int value){
  ncbWndExtra(address(),value);
  return this;
}
