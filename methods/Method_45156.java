private Optional<Integer> mergePort(final ActualHttpServer thisServer,final ActualHttpServer thatServer){
  Optional<Integer> optionalPort=this.getPort();
  if (optionalPort.isPresent()) {
    if (optionalPort.get() != 0) {
      return optionalPort;
    }
  }
  return thatServer.getPort();
}
