public static ImmutableSet<String> available(Project project,Collection<File> files){
  return files.stream().filter(File::exists).map(f -> getRelativePath(project.getProjectDir(),f)).collect(MoreCollectors.toImmutableSet());
}
