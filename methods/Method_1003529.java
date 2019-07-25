/** 
 * Name for  {@link java.util.Date}.
 * @param conversionContext Conversion context
 * @return Name or fully-qualified name.
 */
public static String date(ConversionContext conversionContext){
  return typeReferenceName(conversionContext,java.util.Date.class);
}
