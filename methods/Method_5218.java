private void resolveUtcTimingElementHttp(UtcTimingElement timingElement,ParsingLoadable.Parser<Long> parser){
  startLoading(new ParsingLoadable<>(dataSource,Uri.parse(timingElement.value),C.DATA_TYPE_TIME_SYNCHRONIZATION,parser),new UtcTimestampCallback(),1);
}
