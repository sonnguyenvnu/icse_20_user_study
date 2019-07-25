/** 
 * Wraps the given  {@link DirectoryReader}. The wrapped reader can filter out document just like delete documents etc. but must not change any term or document content. <p> NOTE: The wrapper has a per-request lifecycle, must delegate  {@link IndexReader#getReaderCacheHelper()}, {@link LeafReader#getCoreCacheHelper()} and must be an instance of {@link FilterDirectoryReader} thateventually exposes the original reader via   {@link FilterDirectoryReader#getDelegate()}. The returned reader is closed once it goes out of scope. </p>
 * @param reader The provided directory reader to be wrapped to add custom functionality
 * @return a new directory reader wrapping the provided directory reader or if no wrapping was performedthe provided directory reader
 */
protected DirectoryReader wrap(DirectoryReader reader) throws IOException {
  return reader;
}
