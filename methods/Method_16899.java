private LocalCacheSelectorCode keys(){
  block.beginControlFlow("if (builder.isStrongKeys())").addStatement("sb.append('S')").nextControlFlow("else").addStatement("sb.append('W')").endControlFlow();
  return this;
}
