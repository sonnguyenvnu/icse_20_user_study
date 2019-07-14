@Override public void onItemClick(int position,View v,TimelineModel item){
  if (getView() == null)   return;
  PullRequest pullRequest=getView().getPullRequest();
  if (pullRequest != null) {
    if (item.getType() == TimelineModel.COMMENT) {
      if (v.getId() == R.id.commentMenu) {
        PopupMenu popupMenu=new PopupMenu(v.getContext(),v);
        popupMenu.inflate(R.menu.comments_menu);
        String username=Login.getUser().getLogin();
        boolean isOwner=CommentsHelper.isOwner(username,pullRequest.getLogin(),item.getComment().getUser().getLogin()) || isCollaborator;
        popupMenu.getMenu().findItem(R.id.delete).setVisible(isOwner);
        popupMenu.getMenu().findItem(R.id.edit).setVisible(isOwner);
        popupMenu.setOnMenuItemClickListener(item1 -> {
          if (getView() == null)           return false;
          if (item1.getItemId() == R.id.delete) {
            getView().onShowDeleteMsg(item.getComment().getId());
          }
 else           if (item1.getItemId() == R.id.reply) {
            getView().onReply(item.getComment().getUser(),item.getComment().getBodyHtml());
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
        FilterIssuesActivity.startActivity(v,pullRequest.getLogin(),pullRequest.getRepoId(),false,true,isEnterprise(),"label:\"" + issueEventModel.getLabel().getName() + "\"");
      }
 else       if (issueEventModel.getMilestone() != null) {
        FilterIssuesActivity.startActivity(v,pullRequest.getLogin(),pullRequest.getRepoId(),false,true,isEnterprise(),"milestone:\"" + issueEventModel.getMilestone().getTitle() + "\"");
      }
 else       if (issueEventModel.getAssignee() != null) {
        FilterIssuesActivity.startActivity(v,pullRequest.getLogin(),pullRequest.getRepoId(),false,true,isEnterprise(),"assignee:\"" + issueEventModel.getAssignee().getLogin() + "\"");
      }
 else       if (issueEventModel.getEvent() == IssueEventType.committed) {
        SchemeParser.launchUri(v.getContext(),issueEventModel.getUrl().replace("git/",""));
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
        boolean isOwner=CommentsHelper.isOwner(username,item.getPullRequest().getLogin(),item.getPullRequest().getUser().getLogin()) || isCollaborator;
        popupMenu.getMenu().findItem(R.id.edit).setVisible(isOwner);
        popupMenu.setOnMenuItemClickListener(item1 -> {
          if (getView() == null)           return false;
          if (item1.getItemId() == R.id.reply) {
            getView().onReply(item.getPullRequest().getUser(),item.getPullRequest().getBodyHtml());
          }
 else           if (item1.getItemId() == R.id.edit) {
            Activity activity=ActivityHelper.getActivity(v.getContext());
            if (activity == null)             return false;
            CreateIssueActivity.startForResult(activity,item.getPullRequest().getLogin(),item.getPullRequest().getRepoId(),item.getPullRequest(),isEnterprise());
          }
 else           if (item1.getItemId() == R.id.share) {
            ActivityHelper.shareUrl(v.getContext(),item.getPullRequest().getHtmlUrl());
          }
          return true;
        }
);
        popupMenu.show();
      }
 else {
        onHandleReaction(v.getId(),item.getPullRequest().getNumber(),ReactionsProvider.HEADER);
      }
    }
 else     if (item.getType() == TimelineModel.GROUP) {
      GroupedReviewModel reviewModel=item.getGroupedReviewModel();
      if (v.getId() == R.id.addCommentPreview) {
        if (getView() != null) {
          EditReviewCommentModel model=new EditReviewCommentModel();
          model.setCommentPosition(-1);
          model.setGroupPosition(position);
          model.setInReplyTo(reviewModel.getId());
          getView().onReplyOrCreateReview(null,null,position,-1,model);
        }
      }
    }
  }
}
