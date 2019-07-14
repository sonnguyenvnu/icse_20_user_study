/** 
 * For unit test purposes.
 * @ExcludeFromJavadoc
 */
static HystrixPlugins create(ClassLoader classLoader,LoggerSupplier logSupplier){
  return new HystrixPlugins(classLoader,logSupplier);
}
