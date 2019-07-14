protected SpanEncoder<SofaTracerSpan> getClientSpanEncoder(){
  return new RpcClientDigestSpanJsonEncoder();
}
