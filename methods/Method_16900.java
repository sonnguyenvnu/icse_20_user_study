private LocalCacheSelectorCode values(){
  block.beginControlFlow("if (builder.isStrongValues())").addStatement("sb.append('S')").nextControlFlow("else").addStatement("sb.append('I')").endControlFlow();
  return this;
}
