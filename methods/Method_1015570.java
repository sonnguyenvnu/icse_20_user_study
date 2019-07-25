public Supplier<? extends UUID> create(){
  return SiteUUID::new;
}
