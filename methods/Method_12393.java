@Nullable @SafeVarargs private final BuildVersion updateBuildVersion(Map<String,?>... sources){
  return Arrays.stream(sources).map(BuildVersion::from).filter(Objects::nonNull).findFirst().orElse(null);
}
