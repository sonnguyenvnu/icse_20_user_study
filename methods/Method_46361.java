protected SpanEncoder<SofaTracerSpan> getServerSpanEncoder(){
  return new RpcServerDigestSpanJsonEncoder();
}
