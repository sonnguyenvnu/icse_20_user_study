@NonNull public static List<FragmentPagerAdapterModel> buildForGists(@NonNull Context context){
  return Stream.of(new FragmentPagerAdapterModel(context.getString(R.string.my_gists),ProfileGistsFragment.newInstance(Login.getUser().getLogin())),new FragmentPagerAdapterModel(context.getString(R.string.starred),StarredGistsFragment.newInstance()),new FragmentPagerAdapterModel(context.getString(R.string.public_gists),GistsFragment.newInstance())).collect(Collectors.toList());
}
