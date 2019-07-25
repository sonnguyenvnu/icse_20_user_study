@JsonCreator static Service create(@JsonProperty("ID") final String id,@JsonProperty("Version") final Version version,@JsonProperty("CreatedAt") final Date createdAt,@JsonProperty("UpdatedAt") final Date updatedAt,@JsonProperty("Spec") final ServiceSpec spec,@JsonProperty("Endpoint") final Endpoint endpoint,@JsonProperty("UpdateStatus") final UpdateStatus updateStatus){
  return new AutoValue_Service(id,version,createdAt,updatedAt,spec,endpoint,updateStatus);
}
