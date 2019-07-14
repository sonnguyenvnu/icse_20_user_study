/** 
 * Initializes the chunk for loading, setting the  {@link HlsSampleStreamWrapper} that will receivesamples as they are loaded.
 * @param output The output that will receive the loaded samples.
 */
public void init(HlsSampleStreamWrapper output){
  this.output=output;
}
