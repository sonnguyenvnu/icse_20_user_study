/** 
 * @deprecated Use {@link MemoryPolicy#isDefaultWritePolicy()} or {@link MemoryPolicy#isDefaultAccessPolicy()}.
 */
@Deprecated public boolean isDefaultPolicy(){
  return expireAfterWrite == DEFAULT_POLICY;
}
