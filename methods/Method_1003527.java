/** 
 * Name for  {@link java.util.Currency}.
 * @param conversionContext Conversion context
 * @return Name or fully-qualified name.
 */
public static String currency(ConversionContext conversionContext){
  return typeReferenceName(conversionContext,Currency.class);
}
