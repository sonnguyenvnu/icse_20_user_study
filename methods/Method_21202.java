public void takeData(final @NonNull CommentsData data){
  final Project project=data.project();
  final List<Comment> comments=data.comments();
  final User user=data.user();
  sections().clear();
  sections().add(Collections.singletonList(project));
  addSection(Observable.from(comments).map(comment -> Pair.create(project,comment)).toList().toBlocking().single());
  if (comments.size() == 0) {
    sections().add(Collections.singletonList(new Pair<>(project,user)));
  }
 else {
    sections().add(Collections.emptyList());
  }
  notifyDataSetChanged();
}
