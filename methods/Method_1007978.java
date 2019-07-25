@JsonCreator static Swarm create(@JsonProperty("ID") final String id,@JsonProperty("Version") final Version version,@JsonProperty("CreatedAt") final Date createdAt,@JsonProperty("UpdatedAt") final Date updatedAt,@JsonProperty("Spec") final SwarmSpec swarmSpec,@JsonProperty("JoinTokens") final JoinTokens joinTokens){
  return new AutoValue_Swarm(id,version,createdAt,updatedAt,swarmSpec,joinTokens);
}
