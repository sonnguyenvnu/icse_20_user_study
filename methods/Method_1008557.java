/** 
 * If there are configured  {@link IndexSearcherWrapper} instances, the {@link IndexSearcher} of the provided engine searchergets wrapped and a new  {@link Engine.Searcher} instances is returned, otherwise the provided {@link Engine.Searcher} is returned.This is invoked each time a  {@link Engine.Searcher} is requested to do an operation. (for example search)
 */
public final Engine.Searcher wrap(Engine.Searcher engineSearcher) throws IOException {
  final ElasticsearchDirectoryReader elasticsearchDirectoryReader=ElasticsearchDirectoryReader.getElasticsearchDirectoryReader(engineSearcher.getDirectoryReader());
  if (elasticsearchDirectoryReader == null) {
    throw new IllegalStateException("Can't wrap non elasticsearch directory reader");
  }
  NonClosingReaderWrapper nonClosingReaderWrapper=new NonClosingReaderWrapper(engineSearcher.getDirectoryReader());
  DirectoryReader reader=wrap(nonClosingReaderWrapper);
  if (reader != nonClosingReaderWrapper) {
    if (reader.getReaderCacheHelper() != elasticsearchDirectoryReader.getReaderCacheHelper()) {
      throw new IllegalStateException("wrapped directory reader doesn't delegate IndexReader#getCoreCacheKey, wrappers must override this method and delegate" + " to the original readers core cache key. Wrapped readers can't be used as cache keys since their are used only per request which would lead to subtle bugs");
    }
    if (ElasticsearchDirectoryReader.getElasticsearchDirectoryReader(reader) != elasticsearchDirectoryReader) {
      throw new IllegalStateException("wrapped directory reader hides actual ElasticsearchDirectoryReader but shouldn't");
    }
  }
  final IndexSearcher origIndexSearcher=engineSearcher.searcher();
  final IndexSearcher innerIndexSearcher=new IndexSearcher(reader);
  innerIndexSearcher.setQueryCache(origIndexSearcher.getQueryCache());
  innerIndexSearcher.setQueryCachingPolicy(origIndexSearcher.getQueryCachingPolicy());
  innerIndexSearcher.setSimilarity(origIndexSearcher.getSimilarity(true));
  final IndexSearcher indexSearcher=wrap(innerIndexSearcher);
  if (reader == nonClosingReaderWrapper && indexSearcher == innerIndexSearcher) {
    return engineSearcher;
  }
 else {
    return new Engine.Searcher(engineSearcher.source(),indexSearcher){
      @Override public void close() throws ElasticsearchException {
        try {
          reader().close();
        }
 catch (        IOException e) {
          throw new ElasticsearchException("failed to close reader",e);
        }
 finally {
          engineSearcher.close();
        }
      }
    }
;
  }
}
