public Address address(){
  return state == State.CLOSED ? null : local_addr;
}
