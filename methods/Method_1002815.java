/** 
 * Returns the  {@link String} representation of this type signature, as described in the classdocumentation.
 */
public String signature(){
  if (typeParameters.isEmpty()) {
    return name;
  }
 else {
    return name + '<' + Joiner.on(", ").join(typeParameters) + '>';
  }
}
