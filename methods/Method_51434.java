/** 
 * Returns a builder for a long integer property. The property descriptor will by default accept any value conforming to the format specified by  {@link Long#parseLong(String)}, e.g.  {@code 1234455678854}. <p>Note that that parser only supports decimal representations, and that neither the character L nor l is permitted to appear at the end of the string as a type indicator, as would be permitted in Java source. <p>Acceptable values may be further refined by  {@linkplain PropertyBuilder#require(PropertyConstraint) adding constraints}. The class  {@link NumericConstraints} provides some useful ready-made constraintsfor that purpose.
 * @param name Name of the property to build
 * @return A new builder
 * @see NumericConstraints
 */
public static GenericPropertyBuilder<Long> longIntProperty(String name){
  return new GenericPropertyBuilder<>(name,ValueParserConstants.LONG_PARSER,Long.class);
}
