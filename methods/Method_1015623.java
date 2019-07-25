public Supplier<? extends IpAddress> create(){
  return IpAddressUUID::new;
}
