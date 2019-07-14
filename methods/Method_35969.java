@JsonProperty("equalToJson") public Object getSerializedEqualToJson(){
  return serializeAsString ? getValue() : expected;
}
