private Collection<Address> extract(AnycastAddress anycastAddress){
  return anycastAddress.findAddresses().orElseGet(deliverManager::getViewMembers);
}
