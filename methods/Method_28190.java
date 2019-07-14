@Override public void onItemClick(int position,View v,TimelineModel item){
  if (getView() != null) {
    Issue issue=getView().getIssue();
    if (issue == null)     return;
    if (item.getType() == TimelineModel.COMMENT) {
      if (v.getId() == R.id.commentMenu) {
        PopupMenu popupMenu=new PopupMenu(v.getContext(),v);
        popupMenu.inflate(R.menu.comments_menu);
        String username=Login.getUser().getLogin();
        boolean isOwner=CommentsHelper.isOwner(username,issue.getLogin(),item.getComment().getUser().getLogin()) || isCollaborator;
        popupMenu.getMenu().findItem(R.id.delete).setVisible(isOwner);
        popupMenu.getMenu().findItem(R.id.edit).setVisible(isOwner);
        popupMenu.setOnMenuItemClickListener(item1 -> {
          if (getView() == null)           return false;
          if (item1.getItemId() == R.id.delete) {
            getView().onShowDeleteMsg(item.getComment().getId());
          }
 else           if (item1.getItemId() == R.id.reply) {
            getView().onReply(item.getComment().getUser(),item.getComment().getBody());
          }
 else           if (item1.getItemId() == R.id.edit) {
            getView().onEditComment(item.getComment());
          }
 else           if (item1.getItemId() == R.id.share) {
            ActivityHelper.shareUrl(v.getContext(),item.getComment().getHtmlUrl());
          }
          return true;
        }
);
        popupMenu.show();
      }
 else {
        onHandleReaction(v.getId(),item.getComment().getId(),ReactionsProvider.COMMENT);
      }
    }
 else     if (item.getType() == TimelineModel.EVENT) {
      GenericEvent issueEventModel=item.getGenericEvent();
      if (issueEventModel.getCommitUrl() != null) {
        SchemeParser.launchUri(v.getContext(),Uri.parse(issueEventModel.getCommitUrl()));
      }
 else       if (issueEventModel.getLabel() != null) {
        FilterIssuesActivity.startActivity(v,issue.getLogin(),issue.getRepoId(),true,true,isEnterprise(),"label:\"" + issueEventModel.getLabel().getName() + "\"");
      }
 else       if (issueEventModel.getMilestone() != null) {
        FilterIssuesActivity.startActivity(v,issue.getLogin(),issue.getRepoId(),true,true,isEnterprise(),"milestone:\"" + issueEventModel.getMilestone().getTitle() + "\"");
      }
 else       if (issueEventModel.getAssignee() != null) {
        FilterIssuesActivity.startActivity(v,issue.getLogin(),issue.getRepoId(),true,true,isEnterprise(),"assignee:\"" + issueEventModel.getAssignee().getLogin() + "\"");
      }
 else {
        SourceModel sourceModel=issueEventModel.getSource();
        if (sourceModel != null) {
          if (sourceModel.getCommit() != null) {
            SchemeParser.launchUri(v.getContext(),sourceModel.getCommit().getUrl());
          }
 else           if (sourceModel.getPullRequest() != null) {
            SchemeParser.launchUri(v.getContext(),sourceModel.getPullRequest().getUrl());
          }
 else           if (sourceModel.getIssue() != null) {
            SchemeParser.launchUri(v.getContext(),sourceModel.getIssue().getHtmlUrl());
          }
 else           if (sourceModel.getRepository() != null) {
            SchemeParser.launchUri(v.getContext(),sourceModel.getRepository().getUrl());
          }
        }
      }
    }
 else     if (item.getType() == TimelineModel.HEADER) {
      if (v.getId() == R.id.commentMenu) {
        PopupMenu popupMenu=new PopupMenu(v.getContext(),v);
        popupMenu.inflate(R.menu.comments_menu);
        String username=Login.getUser().getLogin();
        boolean isOwner=CommentsHelper.isOwner(username,item.getIssue().getLogin(),item.getIssue().getUser().getLogin()) || isCollaborator;
        popupMenu.getMenu().findItem(R.id.edit).setVisible(isOwner);
        popupMenu.setOnMenuItemClickListener(item1 -> {
          if (getView() == null)           return false;
          if (item1.getItemId() == R.id.reply) {
            getView().onReply(item.getIssue().getUser(),item.getIssue().getBody());
          }
 else           if (item1.getItemId() == R.id.edit) {
            Activity activity=ActivityHelper.getActivity(v.getContext());
            if (activity == null)             return false;
            CreateIssueActivity.startForResult(activity,item.getIssue().getLogin(),item.getIssue().getRepoId(),item.getIssue(),isEnterprise());
          }
 else           if (item1.getItemId() == R.id.share) {
            ActivityHelper.shareUrl(v.getContext(),item.getIssue().getHtmlUrl());
          }
          return true;
        }
);
        popupMenu.show();
      }
 else {
        onHandleReaction(v.getId(),item.getIssue().getNumber(),ReactionsProvider.HEADER);
      }
    }
  }
}
