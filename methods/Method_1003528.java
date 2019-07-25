/** 
 * Name for  {@link java.sql.Timestamp}.
 * @param conversionContext Conversion context
 * @return Name or fully-qualified name.
 */
public static String timestamp(ConversionContext conversionContext){
  return typeReferenceName(conversionContext,Timestamp.class);
}
