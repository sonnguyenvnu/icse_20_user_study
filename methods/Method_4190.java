/** 
 * Resets the channel mapping. After calling this method, call  {@link #configure(int,int,int)}to start using the new channel map.
 * @param outputChannels The mapping from input to output channel indices, or {@code null} toleave the input unchanged.
 * @see AudioSink#configure(int,int,int,int,int[],int,int)
 */
public void setChannelMap(@Nullable int[] outputChannels){
  pendingOutputChannels=outputChannels;
}
