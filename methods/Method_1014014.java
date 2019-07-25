@Activate protected void activate(Map<String,Object> props){
  lastKnownInterfaceAddresses=Collections.emptyList();
  modified(props);
}
