@JsonCreator public static Preference create(@JsonProperty("Spread") final Spread spread){
  return new AutoValue_Preference(spread);
}
