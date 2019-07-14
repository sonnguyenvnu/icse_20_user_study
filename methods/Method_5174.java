/** 
 * Clears the playlist. 
 */
public final synchronized void clear(){
  removeMediaSourceRange(0,getSize());
}
