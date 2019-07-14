/** 
 * Returns true if the given tree is a generated constructor. * 
 */
public static boolean isGeneratedConstructor(MethodTree tree){
  if (!(tree instanceof JCMethodDecl)) {
    return false;
  }
  return (((JCMethodDecl)tree).mods.flags & Flags.GENERATEDCONSTR) == Flags.GENERATEDCONSTR;
}
