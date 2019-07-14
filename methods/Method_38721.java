private void checkClassName(final List<String> classnameWhitelist,final String className){
  if (classnameWhitelist == null) {
    return;
  }
  classnameWhitelist.forEach(pattern -> {
    if (!Wildcard.equalsOrMatch(className,pattern)) {
      throw new JsonException("Class can't be loaded as it is not whitelisted: " + className);
    }
  }
);
}
