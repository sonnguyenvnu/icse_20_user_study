@NonNull public static List<FragmentPagerAdapterModel> buildForTheme(){
  return Stream.of(new FragmentPagerAdapterModel("",ThemeFragment.Companion.newInstance(R.style.ThemeLight)),new FragmentPagerAdapterModel("",ThemeFragment.Companion.newInstance(R.style.ThemeDark)),new FragmentPagerAdapterModel("",ThemeFragment.Companion.newInstance(R.style.ThemeAmlod)),new FragmentPagerAdapterModel("",ThemeFragment.Companion.newInstance(R.style.ThemeBluish))).collect(Collectors.toList());
}
