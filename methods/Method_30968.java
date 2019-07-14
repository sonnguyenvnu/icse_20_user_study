@NonNull public static BundleBuilder getArgumentsBuilder(@NonNull Fragment fragment){
  Bundle arguments=fragment.getArguments();
  if (arguments == null) {
    arguments=new Bundle();
    fragment.setArguments(arguments);
  }
  return BundleBuilder.buildUpon(arguments);
}
