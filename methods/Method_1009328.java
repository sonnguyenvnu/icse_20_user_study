/** 
 * Creates NamingConvention for builder.
 * @param prefix the prefix for the setter of the builder
 * @return a NamingConvention
 */
public static NamingConvention builder(String prefix){
  return new BuilderNamingConventions(prefix);
}
