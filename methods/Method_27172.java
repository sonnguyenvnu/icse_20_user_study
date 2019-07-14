@Nullable public static Fragment getFragmentByTag(@NonNull FragmentManager fragmentManager,@NonNull String tag){
  return fragmentManager.findFragmentByTag(tag);
}
