@NonNull public static Bundle getYesNoBundle(@NonNull Context context){
  return Bundler.start().put("primary_extra",context.getString(R.string.yes)).put("secondary_extra",context.getString(R.string.no)).end();
}
