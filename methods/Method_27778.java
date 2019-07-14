private void markAsRead(int position,View v,Notification item){
  item.setUnread(false);
  manageDisposable(item.save(item));
  sendToView(view -> view.onRemove(position));
  ReadNotificationService.start(v.getContext(),item.getId());
}
