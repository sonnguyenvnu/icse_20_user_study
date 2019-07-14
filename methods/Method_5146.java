/** 
 * Returns the media chunk index corresponding to a given primary sample index.
 * @param primarySampleIndex The primary sample index for which the corresponding media chunkindex is required.
 * @param minChunkIndex A minimum chunk index from which to start searching, or -1 if no hint canbe provided.
 * @return The index of the media chunk corresponding to the sample index, or -1 if the list ofmedia chunks is empty, or  {@code minChunkIndex} if the sample precedes the first chunk inthe search (i.e. the chunk at  {@code minChunkIndex}, or at index 0 if  {@code minChunkIndex}is -1.
 */
private int primarySampleIndexToMediaChunkIndex(int primarySampleIndex,int minChunkIndex){
  for (int i=minChunkIndex + 1; i < mediaChunks.size(); i++) {
    if (mediaChunks.get(i).getFirstSampleIndex(0) > primarySampleIndex) {
      return i - 1;
    }
  }
  return mediaChunks.size() - 1;
}
