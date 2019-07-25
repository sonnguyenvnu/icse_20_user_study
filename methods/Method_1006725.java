/** 
 * Look up a type variable (e.g. "T") in the defining method and/or enclosing class' type parameters, and return the type parameter with the same name (e.g. "T extends com.xyz.Cls").
 * @return the type parameter (e.g. "T extends com.xyz.Cls", or simply "T" if the type parameter does not haveany bounds).
 * @throws IllegalArgumentException if a type parameter with the same name as the type variable could not be found in the defining method or the enclosing class.
 */
public TypeParameter resolve(){
  if (containingMethodSignature != null && containingMethodSignature.typeParameters != null && !containingMethodSignature.typeParameters.isEmpty()) {
    for (    final TypeParameter typeParameter : containingMethodSignature.typeParameters) {
      if (typeParameter.name.equals(this.name)) {
        return typeParameter;
      }
    }
  }
  final ClassInfo containingClassInfo=getClassInfo();
  if (containingClassInfo == null) {
    throw new IllegalArgumentException("Could not find ClassInfo object for " + definingClassName);
  }
  final ClassTypeSignature containingClassSignature=containingClassInfo.getTypeSignature();
  if (containingClassSignature != null && containingClassSignature.typeParameters != null && !containingClassSignature.typeParameters.isEmpty()) {
    for (    final TypeParameter typeParameter : containingClassSignature.typeParameters) {
      if (typeParameter.name.equals(this.name)) {
        return typeParameter;
      }
    }
  }
  throw new IllegalArgumentException("Could not resolve " + name + " against parameters of the defining method or enclosing class");
}
