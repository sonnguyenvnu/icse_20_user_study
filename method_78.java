/** 
 * Get the node under which available bookies are stored.
 * @return Node under which available bookies are stored.
 */
@Deprecated public String _XXXXX_(){
  return getZkLedgersRootPath() + "/" + AVAILABLE_NODE;
}