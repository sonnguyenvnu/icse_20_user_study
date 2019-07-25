/** 
 * Currently unused since recompiling from method-only decompile is already unsupported <i>(for now)</i>.
 * @param cn Node to extract method from.
 * @return ClassNode containing only the {@link #mn target method}.
 */
@SuppressWarnings("unused") private ClassNode strip(ClassNode cn){
  ClassNode copy=new ClassNode();
  copy.visit(cn.version,cn.access,cn.name,cn.signature,cn.superName,cn.interfaces.stream().toArray(String[]::new));
  copy.methods.add(mn);
  return copy;
}
