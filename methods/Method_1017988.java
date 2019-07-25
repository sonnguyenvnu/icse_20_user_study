/** 
 * Creates a writer for saving the specified non-streaming Spark  {@code DataFrame}.
 */
@Nonnull public static KyloCatalogWriter<DataFrame> write(@Nonnull final DataFrame df){
  return client().write(df);
}
