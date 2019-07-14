/** 
 * Sets methods.
 * @param methods the methods
 * @return the methods
 */
public S setMethods(List<MethodConfig> methods){
  if (this.methods == null) {
    this.methods=new ConcurrentHashMap<String,MethodConfig>();
  }
  if (methods != null) {
    for (    MethodConfig methodConfig : methods) {
      this.methods.put(methodConfig.getName(),methodConfig);
    }
  }
  return castThis();
}
