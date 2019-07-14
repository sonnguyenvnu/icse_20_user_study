/** 
 * config default type key
 * @since 1.2.14
 */
public static void setDefaultTypeKey(String typeKey){
  DEFAULT_TYPE_KEY=typeKey;
  ParserConfig.global.symbolTable.addSymbol(typeKey,0,typeKey.length(),typeKey.hashCode(),true);
}
