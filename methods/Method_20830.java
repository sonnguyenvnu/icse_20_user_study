public static @NonNull String deadlineCountdownDetail(final @NonNull Project project,final @NonNull Context context,final @NonNull KSString ksString){
  return ksString.format(context.getString(R.string.discovery_baseball_card_time_left_to_go),"time_left",deadlineCountdownUnit(project,context));
}
