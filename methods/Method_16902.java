private LocalCacheSelectorCode stats(){
  block.beginControlFlow("if (builder.isRecordingStats())").addStatement("sb.append('S')").endControlFlow();
  return this;
}
