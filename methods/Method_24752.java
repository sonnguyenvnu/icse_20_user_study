public static IBinding resolveBinding(SimpleName node){
  IBinding binding=node.resolveBinding();
  if (binding == null)   return null;
  if (binding.getKind() == IBinding.TYPE) {
    ASTNode context=node;
    while (isNameOrType(context) && !context.getLocationInParent().getId().equals("typeArguments")) {
      context=context.getParent();
    }
switch (context.getNodeType()) {
case ASTNode.METHOD_DECLARATION:
      MethodDeclaration decl=(MethodDeclaration)context;
    if (decl.isConstructor()) {
      binding=decl.resolveBinding();
    }
  break;
case ASTNode.CLASS_INSTANCE_CREATION:
ClassInstanceCreation cic=(ClassInstanceCreation)context;
binding=cic.resolveConstructorBinding();
break;
}
}
if (binding == null) return null;
switch (binding.getKind()) {
case IBinding.TYPE:
ITypeBinding type=(ITypeBinding)binding;
if (type.isParameterizedType() || type.isRawType()) {
binding=type.getErasure();
}
break;
case IBinding.METHOD:
IMethodBinding method=(IMethodBinding)binding;
ITypeBinding declaringClass=method.getDeclaringClass();
if (declaringClass.isParameterizedType() || declaringClass.isRawType()) {
IMethodBinding[] methods=declaringClass.getErasure().getDeclaredMethods();
IMethodBinding generic=Arrays.stream(methods).filter(method::overrides).findAny().orElse(null);
if (generic != null) method=generic;
}
if (method.isParameterizedMethod() || method.isRawMethod()) {
method=method.getMethodDeclaration();
}
binding=method;
break;
}
return binding;
}
