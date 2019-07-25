@JsonCreator public static Placement create(@JsonProperty("Constraints") final List<String> constraints,@JsonProperty("Preferences") final List<Preference> preferences){
  final ImmutableList<String> constraintsT=constraints == null ? null : ImmutableList.copyOf(constraints);
  final ImmutableList<Preference> preferencesT=preferences == null ? null : ImmutableList.copyOf(preferences);
  return new AutoValue_Placement(constraintsT,preferencesT);
}
