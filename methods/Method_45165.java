private static Maps.EntryTransformer<String,RequestExtractor<?>,Variable> toVariable(){
  return new Maps.EntryTransformer<String,RequestExtractor<?>,Variable>(){
    @Override @SuppressWarnings("unchecked") public Variable transformEntry(    final String key,    final RequestExtractor<?> value){
      return new ExtractorVariable(value);
    }
  }
;
}
