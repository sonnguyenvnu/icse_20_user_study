/** 
 * Returns method signature for some method. If signature is not found, returns <code>null</code>. Founded signatures means that those method can be proxyfied.
 */
public MethodSignatureVisitor lookupMethodSignatureVisitor(final int access,final String name,final String desc,final String className){
  String key=ProxettaAsmUtil.createMethodSignaturesKey(access,name,desc,className);
  return methodSignatures.get(key);
}
