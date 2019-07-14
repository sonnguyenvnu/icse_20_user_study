@NonNull public static ArrayList<String> getUsersByTimeline(@NonNull List<TimelineModel> comments){
  return Stream.of(comments).filter(timelineModel -> timelineModel.getComment() != null && timelineModel.getComment().getUser() != null).map(comment -> comment.getComment().getUser().getLogin()).distinct().collect(Collectors.toCollection(ArrayList::new));
}
