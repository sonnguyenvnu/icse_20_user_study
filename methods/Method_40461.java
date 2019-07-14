/** 
 * Configures whether the indexer should abort with an exception when it encounters an internal error or unexpected program state.  Normally the indexer attempts to continue indexing, on the assumption that having an index with mostly good data is better than having no index at all. Enabling aggressive assertions is useful for debugging the indexer.
 */
public void enableAggressiveAssertions(boolean enable){
  aggressiveAssertions=enable;
}
