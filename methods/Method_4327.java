/** 
 * Returns whether the buffer is only able to hold flags, meaning  {@link #data} is null andits replacement mode is  {@link #BUFFER_REPLACEMENT_MODE_DISABLED}.
 */
public final boolean isFlagsOnly(){
  return data == null && bufferReplacementMode == BUFFER_REPLACEMENT_MODE_DISABLED;
}
