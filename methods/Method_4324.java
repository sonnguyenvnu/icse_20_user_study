/** 
 * Removes the  {@code flag} from this buffer's flags, if it is set.
 * @param flag The flag to remove.
 */
public final void clearFlag(@C.BufferFlags int flag){
  flags&=~flag;
}
