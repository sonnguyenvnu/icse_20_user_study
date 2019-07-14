/** 
 * Select maximally specific method. https://docs.oracle.com/javase/specs/jls/se7/html/jls-15.html#jls-15.12.2.5
 */
public static MethodType selectAmongMaximallySpecific(MethodType first,MethodType second){
  if (first.isAbstract()) {
    if (second.isAbstract()) {
      return first;
    }
 else {
      return second;
    }
  }
 else   if (second.isAbstract()) {
    return first;
  }
 else {
    return first;
  }
}
