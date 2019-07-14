static UBreak create(@Nullable CharSequence label){
  return new AutoValue_UBreak((label == null) ? null : StringName.of(label));
}
