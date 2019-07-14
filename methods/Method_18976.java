public static ImmutableList<TagModel> extractTagsFromSpecClass(Types types,TypeElement element){
  final List<? extends TypeMirror> interfaces=element.getInterfaces();
  final List<TagModel> tags;
  if (interfaces != null) {
    tags=interfaces.stream().map(t -> ((DeclaredType)t).asElement()).map(t -> newTagModel(t,types)).collect(Collectors.toList());
  }
 else {
    tags=Collections.emptyList();
  }
  return ImmutableList.copyOf(tags);
}
