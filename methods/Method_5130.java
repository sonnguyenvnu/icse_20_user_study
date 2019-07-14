/** 
 * Initializes the chunk for loading, setting the  {@link BaseMediaChunkOutput} that will receivesamples as they are loaded.
 * @param output The output that will receive the loaded media samples.
 */
public void init(BaseMediaChunkOutput output){
  this.output=output;
  firstSampleIndices=output.getWriteIndices();
}
