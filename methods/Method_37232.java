/** 
 * Reference: http://commons.apache.org/proper/commons-beanutils/
 */
@Benchmark public Object apacheBeanUtils() throws Exception {
  return PropertyUtils.getNestedProperty(javaBean,fieldName);
}
