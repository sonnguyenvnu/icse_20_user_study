/** 
 * Called when the a new chunk in the current media stream has been loaded.
 * @param chunk The chunk whose load has been completed.
 */
void onChunkLoadCompleted(Chunk chunk){
  if (lastLoadedChunkEndTimeUs != C.TIME_UNSET || chunk.endTimeUs > lastLoadedChunkEndTimeUs) {
    lastLoadedChunkEndTimeUs=chunk.endTimeUs;
  }
}
