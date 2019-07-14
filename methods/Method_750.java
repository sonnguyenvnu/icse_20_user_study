private static boolean getArgument(Type[] typeArgs,TypeVariable[] typeVariables,Type[] arguments){
  if (arguments == null || typeVariables.length == 0) {
    return false;
  }
  boolean changed=false;
  for (int i=0; i < typeArgs.length; ++i) {
    Type typeArg=typeArgs[i];
    if (typeArg instanceof ParameterizedType) {
      ParameterizedType p_typeArg=(ParameterizedType)typeArg;
      Type[] p_typeArg_args=p_typeArg.getActualTypeArguments();
      boolean p_changed=getArgument(p_typeArg_args,typeVariables,arguments);
      if (p_changed) {
        typeArgs[i]=new ParameterizedTypeImpl(p_typeArg_args,p_typeArg.getOwnerType(),p_typeArg.getRawType());
        changed=true;
      }
    }
 else     if (typeArg instanceof TypeVariable) {
      for (int j=0; j < typeVariables.length; ++j) {
        if (typeArg.equals(typeVariables[j])) {
          typeArgs[i]=arguments[j];
          changed=true;
        }
      }
    }
  }
  return changed;
}
