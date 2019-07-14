private LocalCacheSelectorCode maximum(){
  block.beginControlFlow("if (builder.evicts())").addStatement("sb.append('M')").beginControlFlow("if (builder.isWeighted())").addStatement("sb.append('W')").nextControlFlow("else").addStatement("sb.append('S')").endControlFlow().endControlFlow();
  return this;
}
