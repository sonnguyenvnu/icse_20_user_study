@JsonCreator public static RegistryAuth create(@JsonProperty("username") final String username,@JsonProperty("password") final String password,@JsonProperty("email") final String email,@JsonProperty("serveraddress") final String serveraddress,@JsonProperty("identitytoken") final String identitytoken,@JsonProperty("auth") final String auth){
  final Builder builder;
  if (auth != null) {
    builder=forAuth(auth);
  }
 else {
    builder=builder().username(username).password(password);
  }
  return builder.email(email).serverAddress(serveraddress).identityToken(identitytoken).build();
}
