/** 
 * Handles the  {@code} "Destination" : { "path" : "/path/to/mount" } }} variant.
 * @param path the destination path of the bind mounted volume
 * @return a volume instance referring to the given path.
 */
@Nonnull @JsonCreator public static Volume parse(@JsonProperty("path") String path){
  return new Volume(path);
}
