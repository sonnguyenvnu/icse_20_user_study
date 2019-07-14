@Override public @NonNull Observable<List<ProjectNotification>> fetchProjectNotifications(){
  return this.service.projectNotifications().lift(apiErrorOperator()).subscribeOn(Schedulers.io());
}
