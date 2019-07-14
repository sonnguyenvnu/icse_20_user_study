/** 
 * This method is added because sometimes it's hard to generate integer keys that are not prone to collision. If a ReconJob class cannot guarantee no collision of integer keys, then that class should override this new method.
 */
public String getStringKey(){
  return Integer.toString(getKey());
}
