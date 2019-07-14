@NonNull public static List<GroupedNotificationModel> onlyNotifications(@Nullable List<Notification> items){
  if (items == null || items.isEmpty())   return new ArrayList<>();
  return Stream.of(items).map(GroupedNotificationModel::new).collect(Collectors.toList());
}
