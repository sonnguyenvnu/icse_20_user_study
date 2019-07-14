@Nullable public static Set<String> getStringSet(@NonNull Entry<Set<String>> entry){
  return getSharedPrefs().getStringSet(entry.getKey(),entry.getDefaultValue());
}
