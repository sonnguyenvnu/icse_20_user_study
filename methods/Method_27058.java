@NonNull public static List<FragmentPagerAdapterModel> buildForDrawer(@NonNull Context context){
  return Stream.of(new FragmentPagerAdapterModel(context.getString(R.string.menu_label),new MainDrawerFragment()),new FragmentPagerAdapterModel(context.getString(R.string.profile),new AccountDrawerFragment())).toList();
}
