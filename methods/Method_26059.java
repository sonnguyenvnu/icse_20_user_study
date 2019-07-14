private static boolean isVerboseLogMessage(VisitorState state){
  return Streams.stream(state.getPath()).anyMatch(LiteProtoToString::isVerboseLogMessage);
}
