@NonNull public static List<ReactionsModel> getReaction(@Nullable List<PullRequestTimelineQuery.ReactionGroup1> reactions){
  List<ReactionsModel> models=new ArrayList<>();
  if (reactions != null && !reactions.isEmpty()) {
    for (    PullRequestTimelineQuery.ReactionGroup1 reaction : reactions) {
      ReactionsModel model=new ReactionsModel();
      model.setContent(reaction.content().name());
      model.setViewerHasReacted(reaction.viewerHasReacted());
      model.setTotal_count(reaction.users().totalCount());
      models.add(model);
    }
  }
  return models;
}
