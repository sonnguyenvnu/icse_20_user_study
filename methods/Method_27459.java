@Override public void bind(@NonNull TimelineModel timelineModel){
  GenericEvent issueEventModel=timelineModel.getGenericEvent();
  IssueEventType event=issueEventModel.getEvent();
  if (issueEventModel.getAssignee() != null && issueEventModel.getAssigner() != null) {
    avatarLayout.setUrl(issueEventModel.getAssigner().getAvatarUrl(),issueEventModel.getAssigner().getLogin(),false,LinkParserHelper.isEnterprise(issueEventModel.getUrl()));
  }
 else {
    if (event != IssueEventType.committed) {
      avatarLayout.setVisibility(View.VISIBLE);
      if (issueEventModel.getActor() != null) {
        avatarLayout.setUrl(issueEventModel.getActor().getAvatarUrl(),issueEventModel.getActor().getLogin(),false,LinkParserHelper.isEnterprise(issueEventModel.getUrl()));
      }
 else       if (issueEventModel.getAuthor() != null) {
        avatarLayout.setUrl(issueEventModel.getAuthor().getAvatarUrl(),issueEventModel.getAuthor().getLogin(),false,LinkParserHelper.isEnterprise(issueEventModel.getUrl()));
      }
    }
 else {
      avatarLayout.setVisibility(View.GONE);
    }
  }
  if (event != null) {
    stateImage.setContentDescription(event.name());
    stateImage.setImageResource(event.getIconResId());
  }
  if (event != null) {
    stateText.setText(TimelineProvider.getStyledEvents(issueEventModel,itemView.getContext(),isMerged));
  }
 else {
    stateText.setText("");
    stateImage.setImageResource(R.drawable.ic_label);
  }
}
