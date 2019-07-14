@Override public CodeBlock render(int sdk){
  ResourceMethod method=type.methodForSdk(sdk);
  if (method.typeName == null) {
    if (method.requiresResources) {
      return CodeBlock.of("target.$L = res.$L($L)",name,method.name,id.code);
    }
    return CodeBlock.of("target.$L = context.$L($L)",name,method.name,id.code);
  }
  if (method.requiresResources) {
    return CodeBlock.of("target.$L = $T.$L(res, $L)",name,method.typeName,method.name,id.code);
  }
  return CodeBlock.of("target.$L = $T.$L(context, $L)",name,method.typeName,method.name,id.code);
}
