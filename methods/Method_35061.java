/** 
 * Creates ParsingFetcher for raw data type Fetcher and Raw data Parser.
 */
public static final <Parsed,Raw,Key>ParsingFetcher<Parsed,Raw,Key> from(@Nonnull Fetcher<Raw,Key> fetcher,@Nonnull Parser<Raw,Parsed> parser){
  return new ParsingFetcher(fetcher,parser);
}
