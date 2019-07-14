private void markAsRead(int position,View v,Notification item){
  item.setUnread(false);
  manageDisposable(item.save(item));
  sendToView(view -> view.onUpdateReadState(new GroupedNotificationModel(item),position));
  ReadNotificationService.start(v.getContext(),item.getId());
}
