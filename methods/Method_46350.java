protected String baggageSerialized(SofaTracerSpanContext spanContext){
  return spanContext.getBizSerializedBaggage();
}
