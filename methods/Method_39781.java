/** 
 * Parses signature for generic information and returns a map where key is generic name and value is raw type. Returns an empty map if signature does not define any generics.
 */
public Map<String,String> parseSignatureForGenerics(final String signature,final boolean isInterface){
  if (signature == null) {
    return Collections.emptyMap();
  }
  final int indexOfBracket=signature.indexOf("<");
  final String declaringClass;
  if (indexOfBracket > 0) {
    declaringClass=signature.substring(1,indexOfBracket) + ":";
  }
 else {
    declaringClass=null;
  }
  final SignatureReader sr=new SignatureReader(signature);
  final StringBuilder sb=new StringBuilder();
  TraceSignatureVisitor v=new TraceSignatureVisitor(sb,isInterface){
    @Override public void visitFormalTypeParameter(    final String name){
      genericName=name;
      super.visitFormalTypeParameter(name);
    }
    @Override public void visitClassType(    final String name){
      classTypeCounter++;
      if (genericName != null) {
        genericsMap.put(genericName,'L' + name + ';');
        genericName=null;
      }
 else {
        if (declaringClass != null) {
          genericsMap.put(declaringClass + (classTypeCounter - 1),'L' + name + ';');
        }
      }
      super.visitClassType(name);
    }
  }
;
  sr.accept(v);
  return genericsMap;
}
