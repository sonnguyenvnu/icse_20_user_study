@Override public @NonNull Observable<ProjectNotification> updateProjectNotifications(final @NonNull ProjectNotification projectNotification,final boolean checked){
  return Observable.just(projectNotification.toBuilder().email(checked).mobile(checked).build());
}
