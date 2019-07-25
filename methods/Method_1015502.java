public Supplier<? extends Header> create(){
  return EncryptHeader::new;
}
