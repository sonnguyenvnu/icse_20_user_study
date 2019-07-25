@JsonCreator static MemoryStats create(@JsonProperty("stats") final Stats stats,@JsonProperty("max_usage") final Long maxUsage,@JsonProperty("usage") final Long usage,@JsonProperty("failcnt") Long failcnt,@JsonProperty("limit") Long limit){
  return new AutoValue_MemoryStats(stats,maxUsage,usage,failcnt,limit);
}
