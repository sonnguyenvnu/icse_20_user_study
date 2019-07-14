private LocalCacheSelectorCode expires(){
  block.beginControlFlow("if (builder.expiresAfterAccess() || builder.expiresVariable())").addStatement("sb.append('A')").endControlFlow().beginControlFlow("if (builder.expiresAfterWrite())").addStatement("sb.append('W')").endControlFlow().beginControlFlow("if (builder.refreshes())").addStatement("sb.append('R')").endControlFlow();
  return this;
}
