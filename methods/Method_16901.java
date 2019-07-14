private LocalCacheSelectorCode removalListener(){
  block.beginControlFlow("if (builder.removalListener != null)").addStatement("sb.append('L')").endControlFlow();
  return this;
}
