/** 
 * Creates a writer for saving the specified non-streaming Spark  {@code DataFrame} to the specified data set.<p>Use the writer to override properties of the data set. Then call  {@link KyloCatalogWriter#save() save()} to update the data set.</p>
 * @param source   the source data set
 * @param targetId identifier of the pre-defined target data set
 * @return a write pre-configured to access the target data set
 */
@Nonnull public static KyloCatalogWriter<DataFrame> write(@Nonnull final DataFrame source,@Nonnull final String targetId){
  return client().write(source,targetId);
}
