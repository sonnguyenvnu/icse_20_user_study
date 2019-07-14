@Override public void bind(@NonNull Event eventsModel){
  appendAvatar(eventsModel);
  SpannableBuilder spannableBuilder=SpannableBuilder.builder();
  appendActor(eventsModel,spannableBuilder);
  description.setMaxLines(2);
  description.setText("");
  description.setVisibility(View.GONE);
  if (eventsModel.getType() != null) {
    EventsType type=eventsModel.getType();
    if (type == EventsType.WatchEvent) {
      appendWatch(spannableBuilder,type,eventsModel);
    }
 else     if (type == EventsType.CreateEvent) {
      appendCreateEvent(spannableBuilder,eventsModel);
    }
 else     if (type == EventsType.CommitCommentEvent) {
      appendCommitComment(spannableBuilder,eventsModel);
    }
 else     if (type == EventsType.DownloadEvent) {
      appendDownloadEvent(spannableBuilder,eventsModel);
    }
 else     if (type == EventsType.FollowEvent) {
      appendFollowEvent(spannableBuilder,eventsModel);
    }
 else     if (type == EventsType.ForkEvent) {
      appendForkEvent(spannableBuilder,eventsModel);
    }
 else     if (type == EventsType.GistEvent) {
      appendGistEvent(spannableBuilder,eventsModel);
    }
 else     if (type == EventsType.GollumEvent) {
      appendGollumEvent(spannableBuilder,eventsModel);
    }
 else     if (type == EventsType.IssueCommentEvent) {
      appendIssueCommentEvent(spannableBuilder,eventsModel);
    }
 else     if (type == EventsType.IssuesEvent) {
      appendIssueEvent(spannableBuilder,eventsModel);
    }
 else     if (type == EventsType.MemberEvent) {
      appendMemberEvent(spannableBuilder,eventsModel);
    }
 else     if (type == EventsType.PublicEvent) {
      appendPublicEvent(spannableBuilder,eventsModel);
    }
 else     if (type == EventsType.PullRequestEvent) {
      appendPullRequestEvent(spannableBuilder,eventsModel);
    }
 else     if (type == EventsType.PullRequestReviewCommentEvent) {
      appendPullRequestReviewCommentEvent(spannableBuilder,eventsModel);
    }
 else     if (type == EventsType.PullRequestReviewEvent) {
      appendPullRequestReviewCommentEvent(spannableBuilder,eventsModel);
    }
 else     if (type == EventsType.RepositoryEvent) {
      appendPublicEvent(spannableBuilder,eventsModel);
    }
 else     if (type == EventsType.PushEvent) {
      appendPushEvent(spannableBuilder,eventsModel);
    }
 else     if (type == EventsType.TeamAddEvent) {
      appendTeamEvent(spannableBuilder,eventsModel);
    }
 else     if (type == EventsType.DeleteEvent) {
      appendDeleteEvent(spannableBuilder,eventsModel);
    }
 else     if (type == EventsType.ReleaseEvent) {
      appendReleaseEvent(spannableBuilder,eventsModel);
    }
 else     if (type == EventsType.ForkApplyEvent) {
      appendForkApplyEvent(spannableBuilder,eventsModel);
    }
 else     if (type == EventsType.OrgBlockEvent) {
      appendOrgBlockEvent(spannableBuilder,eventsModel);
    }
 else     if (type == EventsType.ProjectCardEvent) {
      appendProjectCardEvent(spannableBuilder,eventsModel,false);
    }
 else     if (type == EventsType.ProjectColumnEvent) {
      appendProjectCardEvent(spannableBuilder,eventsModel,true);
    }
 else     if (type == EventsType.OrganizationEvent) {
      appendOrganizationEvent(spannableBuilder,eventsModel);
    }
 else     if (type == EventsType.ProjectEvent) {
      appendProjectCardEvent(spannableBuilder,eventsModel,false);
    }
    date.setGravity(Gravity.CENTER);
    date.setEventsIcon(type.getDrawableRes());
  }
  title.setText(spannableBuilder);
  date.setText(ParseDateFormat.getTimeAgo(eventsModel.getCreatedAt()));
}
