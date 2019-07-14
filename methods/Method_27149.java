@NonNull public static Observable<List<TimelineModel>> construct(@Nullable List<Comment> comments){
  if (comments == null || comments.isEmpty())   return Observable.empty();
  return Observable.fromIterable(comments).map(TimelineModel::new).toList().toObservable();
}
