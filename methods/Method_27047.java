@NonNull public static List<FragmentPagerAdapterModel> buildForNotifications(@NonNull Context context){
  return Stream.of(new FragmentPagerAdapterModel(context.getString(R.string.unread),new UnreadNotificationsFragment()),new FragmentPagerAdapterModel(context.getString(R.string.all),AllNotificationsFragment.newInstance()),new FragmentPagerAdapterModel(context.getString(R.string.app_name),new FastHubNotificationsFragment())).collect(Collectors.toList());
}
