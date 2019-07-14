/** 
 * Parses a qualified name given in the format defined for this implementation. The format is specified by a regex pattern (see  {@link #FORMAT}). Examples: <p> {@code com.company.MyClass$Nested#myMethod(String, int)}<ul> <li> Packages are separated by full stops; <li> Nested classes are separated by a dollar symbol; <li> The optional method suffix is separated from the class with a hashtag; <li> Method arguments are separated by a comma and a single space. </ul> <p> {@code MyClass$Nested}<ul> <li> The qualified name of a class in the unnamed package starts with the class name. </ul> <p> {@code com.foo.Class$1LocalClass}<ul> <li> A local class' qualified name is assigned an index which identifies it within the scope of its enclosing class. The index is displayed after the separating dollar symbol. </ul>
 * @param name The name to parse.
 * @return A qualified name instance corresponding to the parsed string.
 */
public static JavaQualifiedName ofString(String name){
  return ofStringWithClassLoader(name,null);
}
