@JsonCreator static VolumeList create(@JsonProperty("Volumes") final List<Volume> volumes,@JsonProperty("Warnings") final List<String> warnings){
  final ImmutableList<Volume> volumesCopy=volumes == null ? null : ImmutableList.copyOf(volumes);
  final ImmutableList<String> warningsCopy=warnings == null ? null : ImmutableList.copyOf(warnings);
  return new AutoValue_VolumeList(volumesCopy,warningsCopy);
}
