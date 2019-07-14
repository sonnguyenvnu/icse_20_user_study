/** 
 * There are a few cases of classes with typeArgs (e.g.  {@literal MyClass<SomeClass, ..>}) where <p>TypeName.get() throws an error like: "com.sun.tools.javac.code.Symbol$CompletionFailure: class file for SomeClass not found". Therefore we manually get the qualified name and create the TypeName from the path String.
 */
private static TypeName safelyGetTypeName(TypeMirror t){
  TypeName typeName;
  try {
    typeName=TypeName.get(t);
  }
 catch (  Exception e) {
    final String qualifiedName;
    if (t instanceof DeclaredType) {
      qualifiedName=((TypeElement)((DeclaredType)t).asElement()).getQualifiedName().toString();
    }
 else {
      String tmp=t.toString();
      qualifiedName=tmp.substring(0,tmp.indexOf('<'));
    }
    typeName=ClassName.bestGuess(qualifiedName);
  }
  return typeName;
}
