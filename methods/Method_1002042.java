@JsonCreator public static User named(@NotNull @JsonProperty("name") String name){
  return new AutoValue_User(name);
}
