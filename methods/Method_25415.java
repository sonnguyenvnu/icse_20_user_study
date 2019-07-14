/** 
 * Matches any node that is directly an implementation, but not extension, of the given Class. <p>E.x.  {@code class C implements I} will match, but {@code class C extends A} will not.<p>Additionally, this will only match <i>direct</i> implementations of interfaces. E.g. the following will not match: <p> {@code} interface I1 interface I2 extends I1 {} class C implements I2 {} ... isDirectImplementationOf(I1).match(\/*class tree for C*\/); // will not match }
 */
public static Matcher<ClassTree> isDirectImplementationOf(String clazz){
  Matcher<Tree> isProvidedType=isSameType(clazz);
  return new IsDirectImplementationOf(isProvidedType);
}
