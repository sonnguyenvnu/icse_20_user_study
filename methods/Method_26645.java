static UContinue create(@Nullable CharSequence label){
  return new AutoValue_UContinue((label == null) ? null : StringName.of(label));
}
