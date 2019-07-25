public List<MultiAddress> bootstrap() throws IOException {
  return ((List<String>)retrieveMap("bootstrap/").get("Peers")).stream().map(x -> new MultiAddress(x)).collect(Collectors.toList());
}
