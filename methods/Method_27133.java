@NonNull public static List<ReactionsModel> getReactionGroup(@Nullable List<PullRequestTimelineQuery.ReactionGroup> reactions){
  List<ReactionsModel> models=new ArrayList<>();
  if (reactions != null && !reactions.isEmpty()) {
    for (    PullRequestTimelineQuery.ReactionGroup reaction : reactions) {
      ReactionsModel model=new ReactionsModel();
      model.setContent(reaction.content().name());
      model.setViewerHasReacted(reaction.viewerHasReacted());
      model.setTotal_count(reaction.users().totalCount());
      models.add(model);
    }
  }
  return models;
}
