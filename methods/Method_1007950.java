@JsonCreator public static DockerCredentialHelperAuth create(@JsonProperty("Username") final String username,@JsonProperty("Secret") final String secret,@JsonProperty("ServerURL") final String serverUrl){
  return new AutoValue_DockerCredentialHelperAuth(username,secret,serverUrl);
}
