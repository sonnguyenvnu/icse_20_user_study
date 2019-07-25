public static SpanContext extract(Message message,Tracer tracer){
  return tracer.extract(Format.Builtin.TEXT_MAP,new QmqMessageExtractAdapter(message));
}
