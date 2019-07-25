@Override protected TokenizerFactory create(Version version){
  if (multiTermComponent != null) {
    return new MultiTermAwareTokenizerFactory(){
      @Override public Tokenizer create(){
        return create.apply(version);
      }
      @Override public Object getMultiTermComponent(){
        return multiTermComponent.apply(version);
      }
    }
;
  }
 else {
    return () -> create.apply(version);
  }
}
