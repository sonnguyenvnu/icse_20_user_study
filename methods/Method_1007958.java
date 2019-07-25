@JsonCreator static ProcessConfig create(@JsonProperty("privileged") final Boolean privileged,@JsonProperty("user") final String user,@JsonProperty("tty") final Boolean tty,@JsonProperty("entrypoint") final String entrypoint,@JsonProperty("arguments") final List<String> arguments){
  return new AutoValue_ProcessConfig(privileged,user,tty,entrypoint,ImmutableList.copyOf(arguments));
}
