/** 
 * @since 1.2.33
 */
public <T>T toJavaObject(TypeReference typeReference){
  Type type=typeReference != null ? typeReference.getType() : null;
  return TypeUtils.cast(this,type,ParserConfig.getGlobalInstance());
}
