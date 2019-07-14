private static Maps.EntryTransformer<String,TextContainer,RequestExtractor<?>> toVariable(){
  return new Maps.EntryTransformer<String,TextContainer,RequestExtractor<?>>(){
    @Override public RequestExtractor<?> transformEntry(    final String key,    final TextContainer value){
      if (value.isRawText()) {
        return var(value.getText());
      }
      return createRequestExtractor(getExtractorMethod(value.getOperation()),value.getText());
    }
  }
;
}
