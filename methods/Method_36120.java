@Override public JettySettings jettySettings(){
  JettySettings.Builder builder=JettySettings.Builder.aJettySettings();
  if (optionSet.hasArgument(JETTY_ACCEPTOR_THREAD_COUNT)) {
    builder=builder.withAcceptors(Integer.parseInt((String)optionSet.valueOf(JETTY_ACCEPTOR_THREAD_COUNT)));
  }
  if (optionSet.hasArgument(JETTY_ACCEPT_QUEUE_SIZE)) {
    builder=builder.withAcceptQueueSize(Integer.parseInt((String)optionSet.valueOf(JETTY_ACCEPT_QUEUE_SIZE)));
  }
  if (optionSet.hasArgument(JETTY_HEADER_BUFFER_SIZE)) {
    builder=builder.withRequestHeaderSize(Integer.parseInt((String)optionSet.valueOf(JETTY_HEADER_BUFFER_SIZE)));
  }
  if (optionSet.hasArgument(JETTY_STOP_TIMEOUT)) {
    builder=builder.withStopTimeout(Long.parseLong((String)optionSet.valueOf(JETTY_STOP_TIMEOUT)));
  }
  return builder.build();
}
