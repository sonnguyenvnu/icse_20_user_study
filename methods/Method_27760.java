@Override public void onItemClick(int position,View v,GroupedNotificationModel model){
  if (getView() == null)   return;
  if (model.getType() == GroupedNotificationModel.ROW) {
    Notification item=model.getNotification();
    if (v.getId() == R.id.markAsRead) {
      if (item.isUnread() && !PrefGetter.isMarkAsReadEnabled()) {
        markAsRead(position,v,item);
      }
    }
 else     if (v.getId() == R.id.unsubsribe) {
      item.setUnread(false);
      manageDisposable(item.save(item));
      sendToView(view -> view.onUpdateReadState(new GroupedNotificationModel(item),position));
      ReadNotificationService.unSubscribe(v.getContext(),item.getId());
    }
 else {
      if (item.getSubject() != null && item.getSubject().getUrl() != null) {
        if (item.isUnread() && !PrefGetter.isMarkAsReadEnabled()) {
          markAsRead(position,v,item);
        }
        if (getView() != null)         getView().onClick(item.getSubject().getUrl());
      }
    }
  }
 else {
    Repo repo=model.getRepo();
    if (repo == null)     return;
    if (v.getId() == R.id.markAsRead) {
      getView().onMarkAllByRepo(repo);
    }
 else {
      RepoPagerActivity.startRepoPager(v.getContext(),new NameParser(repo.getUrl()));
    }
  }
}
