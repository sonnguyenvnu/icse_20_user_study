@Override protected CharFilterFactory create(Version version){
  if (useFilterForMultitermQueries) {
    return new MultiTermAwareCharFilterFactory(){
      @Override public String name(){
        return getName();
      }
      @Override public Reader create(      Reader reader){
        return create.apply(reader,version);
      }
      @Override public Object getMultiTermComponent(){
        return this;
      }
    }
;
  }
  return new CharFilterFactory(){
    @Override public Reader create(    Reader reader){
      return create.apply(reader,version);
    }
    @Override public String name(){
      return getName();
    }
  }
;
}
