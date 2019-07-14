/** 
 * Returns <code>true</code> if underlying buffer was written to using  {@link #getOutputStream()} (as opposed to {@link #getWriter()}. If buffering was not enabled at all, <code>false</code> will be returned, therefore an additional  {@link #isBufferingEnabled() check} is required.
 */
public boolean isBufferStreamBased(){
  return buffer != null && buffer.isUsingStream();
}
