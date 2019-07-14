/** 
 * Adds the  {@code flag} to this buffer's flags.
 * @param flag The flag to add to this buffer's flags, which should be one of the{@code C.BUFFER_FLAG_*} constants.
 */
public final void addFlag(@C.BufferFlags int flag){
  flags|=flag;
}
