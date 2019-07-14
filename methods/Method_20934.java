public static @NonNull ProjectNotification enabled(){
  return ProjectNotification.builder().id(IdFactory.id()).email(true).mobile(true).project(project()).urls(urls()).build();
}
