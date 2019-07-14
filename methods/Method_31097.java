public static boolean isInPortait(@NonNull Context context){
  return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
}
