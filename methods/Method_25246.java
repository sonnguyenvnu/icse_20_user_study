/** 
 * Variables take their values from their past assignments (as far as they can be determined). Additionally, variables of primitive type are always refined to non-null. <p>(This second case is rarely of interest to us. Either the variable is being used as a primitive, in which case we probably wouldn't have bothered to run the nullness checker on it, or it's being used as an Object, in which case the compiler generates a call to  {@code valueOf}(to autobox the value), which triggers  {@link #visitMethodInvocation}.) <p>Edge case:  {@code node} can be a captured local variable accessed from inside a local oranonymous inner class, or possibly from inside a lambda expression (even though these manifest as fields in bytecode). As of 7/2016 this analysis doesn't have any knowledge of captured local variables will essentially assume whatever default is used in  {@link #values}.
 */
@Override Nullness visitLocalVariable(LocalVariableNode node,LocalVariableValues<Nullness> values){
  return hasPrimitiveType(node) || hasNonNullConstantValue(node) ? NONNULL : values.valueOfLocalVariable(node,defaultAssumption);
}
