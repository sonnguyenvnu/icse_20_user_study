@JsonCreator static Mount create(@JsonProperty("Type") final String type,@JsonProperty("Source") final String source,@JsonProperty("Target") final String target,@JsonProperty("ReadOnly") final Boolean readOnly,@JsonProperty("BindOptions") final BindOptions bindOptions,@JsonProperty("VolumeOptions") final VolumeOptions volumeOptions,@JsonProperty("TmpfsOptions") final TmpfsOptions tmpfsOptions){
  return builder().type(type).source(source).target(target).readOnly(readOnly).bindOptions(bindOptions).volumeOptions(volumeOptions).tmpfsOptions(tmpfsOptions).build();
}
