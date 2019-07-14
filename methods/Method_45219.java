private Supplier<ImmutableMap<String,String>> cookieSupplier(){
  return Suppliers.memoize(new Supplier<ImmutableMap<String,String>>(){
    @Override public ImmutableMap<String,String> get(){
      Optional<ImmutableMap<String,String>> cookies=new CookiesRequestExtractor().extract(DefaultHttpRequest.this);
      return cookies.or(emptyMapSupplier());
    }
  }
);
}
