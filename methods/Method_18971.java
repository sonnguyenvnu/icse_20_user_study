/** 
 * @return List of names in order of definition. List may be empty if there are no custom propsdefined. Value may not be present if loading for the given spec model failed, i.e. we don't have inter-stage resources on the class path to facilitate the lookup.
 */
public Optional<ImmutableList<String>> loadNames(Name qualifiedName){
  final Optional<FileObject> resource=getResource(mFiler,StandardLocation.CLASS_PATH,"",BASE_PATH + qualifiedName + FILE_EXT);
  return resource.map(r -> {
    final List<String> props=new ArrayList<>();
    try (BufferedReader reader=new BufferedReader(new InputStreamReader(r.openInputStream()))){
      String line;
      while ((line=reader.readLine()) != null) {
        props.add(line);
      }
    }
 catch (    final IOException err) {
      throw new RuntimeException(err);
    }
    return ImmutableList.copyOf(props);
  }
);
}
