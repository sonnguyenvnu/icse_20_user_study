/** 
 * Factory method to retrieve the default Setter. Groovy has a bug (GROOVY-6286) which does not allow method names and inner classes to have the same name This method fixes Issue #967 and allows Groovy consumers to choose this method and not trigger the bug
 */
public static Setter defaultSetter(){
  return Setter();
}
