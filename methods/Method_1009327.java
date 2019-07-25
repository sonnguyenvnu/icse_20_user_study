/** 
 * Creates NameTransformer for builder.
 * @param prefix the prefix for the setter of the builder
 * @return a NameTransformer
 */
public static NameTransformer builder(String prefix){
  return new BuilderNameTransformer(prefix);
}
