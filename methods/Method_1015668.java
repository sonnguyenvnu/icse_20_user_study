public Supplier<? extends UUID> create(){
  return ExtendedUUID::new;
}
