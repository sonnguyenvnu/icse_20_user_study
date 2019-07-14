@Override protected ApiRequest<NotificationCount> onCreateRequest(){
  mAccount=AccountUtils.getActiveAccount();
  return ApiService.getInstance().getNotificationCount();
}
