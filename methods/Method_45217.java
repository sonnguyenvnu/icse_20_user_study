private Supplier<ImmutableMap<String,String>> formSupplier(){
  return Suppliers.memoize(new Supplier<ImmutableMap<String,String>>(){
    @Override public ImmutableMap<String,String> get(){
      Optional<ImmutableMap<String,String>> forms=new FormsRequestExtractor().extract(DefaultHttpRequest.this);
      return forms.or(emptyMapSupplier());
    }
  }
);
}
