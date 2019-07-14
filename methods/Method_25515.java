/** 
 * Gets the symbol for a member reference. 
 */
@Nullable public static MethodSymbol getSymbol(MemberReferenceTree tree){
  Symbol sym=((JCMemberReference)tree).sym;
  return sym instanceof MethodSymbol ? (MethodSymbol)sym : null;
}
