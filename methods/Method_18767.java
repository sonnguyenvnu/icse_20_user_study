private static TypeName getParameterTypeName(SpecModel specModel,TypeName varArgTypeArgumentTypeName){
  final String rawVarArgType=varArgTypeArgumentTypeName.toString();
  final boolean isKotlinSpec=specModel.getSpecElementType() == SpecElementType.KOTLIN_SINGLETON;
  TypeName varArgTypeName;
  if (isKotlinSpec) {
    final boolean isNotJvmSuppressWildcardsAnnotated=KotlinSpecUtils.isNotJvmSuppressWildcardsAnnotated(rawVarArgType);
    if (!isNotJvmSuppressWildcardsAnnotated) {
      varArgTypeName=varArgTypeArgumentTypeName;
    }
 else {
      final String[] typeParts=rawVarArgType.split(" ");
      if (typeParts.length < 3) {
        varArgTypeName=varArgTypeArgumentTypeName;
      }
 else {
        final String pureTypeName=typeParts[2];
        varArgTypeName=KotlinSpecUtils.buildClassName(pureTypeName);
      }
    }
  }
 else {
    varArgTypeName=varArgTypeArgumentTypeName;
  }
  return varArgTypeName;
}
