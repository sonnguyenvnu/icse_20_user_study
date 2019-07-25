/** 
 * Create an empty builder instance with a default  {@link InitializrConfiguration}.
 * @return a new {@link InitializrMetadataBuilder} instance
 */
public static InitializrMetadataBuilder create(){
  return new InitializrMetadataBuilder(new InitializrConfiguration());
}
