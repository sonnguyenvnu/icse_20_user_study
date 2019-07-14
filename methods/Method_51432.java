/** 
 * Returns a builder for an integer property. The property descriptor will by default accept any value conforming to the format specified by  {@link Integer#parseInt(String)}, e.g.  {@code 1234} or {@code -123}. <p>Note that that parser only supports decimal representations. <p>Acceptable values may be further refined by  {@linkplain PropertyBuilder#require(PropertyConstraint) adding constraints}. The class  {@link NumericConstraints} provides some useful ready-made constraintsfor that purpose.
 * @param name Name of the property to build
 * @return A new builder
 * @see NumericConstraints
 */
public static GenericPropertyBuilder<Integer> intProperty(String name){
  return new GenericPropertyBuilder<>(name,ValueParserConstants.INTEGER_PARSER,Integer.class);
}
