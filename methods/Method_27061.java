@NonNull public static List<GroupedNotificationModel> construct(@Nullable List<Notification> items){
  List<GroupedNotificationModel> models=new ArrayList<>();
  if (items == null || items.isEmpty())   return models;
  Map<Repo,List<Notification>> grouped=Stream.of(items).filter(value -> !value.isUnread()).collect(Collectors.groupingBy(Notification::getRepository,LinkedHashMap::new,Collectors.mapping(o -> o,toList())));
  Stream.of(grouped).filter(repoListEntry -> repoListEntry.getValue() != null && !repoListEntry.getValue().isEmpty()).forEach(repoListEntry -> {
    Repo repo=repoListEntry.getKey();
    List<Notification> notifications=repoListEntry.getValue();
    models.add(new GroupedNotificationModel(repo));
    Stream.of(notifications).sorted((o1,o2) -> o2.getUpdatedAt().compareTo(o1.getUpdatedAt())).forEach(notification -> models.add(new GroupedNotificationModel(notification)));
  }
);
  return models;
}
