@NonNull public static List<FragmentPagerAdapterModel> buildForSearch(@NonNull Context context){
  return Stream.of(new FragmentPagerAdapterModel(context.getString(R.string.repos),SearchReposFragment.newInstance()),new FragmentPagerAdapterModel(context.getString(R.string.users),SearchUsersFragment.newInstance()),new FragmentPagerAdapterModel(context.getString(R.string.issues),SearchIssuesFragment.newInstance()),new FragmentPagerAdapterModel(context.getString(R.string.code),SearchCodeFragment.newInstance())).collect(Collectors.toList());
}
