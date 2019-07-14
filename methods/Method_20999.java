public @NonNull String param(){
  final String slug=slug();
  return slug != null ? slug : String.valueOf(id());
}
