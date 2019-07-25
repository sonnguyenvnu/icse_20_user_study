/** 
 * Name for  {@link java.util.Locale}.
 * @param conversionContext Conversion context
 * @return Name or fully-qualified name.
 */
public static String locale(ConversionContext conversionContext){
  return typeReferenceName(conversionContext,Locale.class);
}
