@JsonCreator static NetworkAttachment create(@JsonProperty("Network") final Network network,@JsonProperty("Addresses") final List<String> addresses){
  final ImmutableList<String> addressesCopy=addresses == null ? ImmutableList.<String>of() : ImmutableList.copyOf(addresses);
  return new AutoValue_NetworkAttachment(network,addressesCopy);
}
