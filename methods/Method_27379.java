@NonNull public static ArrayList<String> getUsers(@NonNull List<Comment> comments){
  return Stream.of(comments).map(comment -> comment.getUser().getLogin()).distinct().collect(Collectors.toCollection(ArrayList::new));
}
