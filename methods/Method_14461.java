@JsonProperty("t") @JsonInclude(Include.NON_NULL) public String getTypeString(){
  if (value instanceof OffsetDateTime || value instanceof LocalDateTime) {
    return "date";
  }
  return null;
}
