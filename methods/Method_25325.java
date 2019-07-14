private static String newArgument(String existingParameters,Collection<String> initializers){
  return newArgument(ImmutableList.<String>builder().add(existingParameters).addAll(initializers).build());
}
