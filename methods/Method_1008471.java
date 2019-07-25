/** 
 * Create a pre-configured tokenizer that may not vary at all.
 * @param name the name of the tokenizer in the api
 * @param create builds the tokenizer
 * @param multiTermComponent null if this tokenizer shouldn't be used for multi-term queries, otherwise a supplier for the{@link TokenFilterFactory} that stands in for this tokenizer in multi-term queries.
 */
public static PreConfiguredTokenizer singleton(String name,Supplier<Tokenizer> create,@Nullable Supplier<TokenFilterFactory> multiTermComponent){
  return new PreConfiguredTokenizer(name,CachingStrategy.ONE,version -> create.get(),multiTermComponent == null ? null : version -> multiTermComponent.get());
}
