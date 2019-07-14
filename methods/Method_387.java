/** 
 * @since 1.2.33
 */
public <T>T toJavaObject(Type type){
  return TypeUtils.cast(this,type,ParserConfig.getGlobalInstance());
}
