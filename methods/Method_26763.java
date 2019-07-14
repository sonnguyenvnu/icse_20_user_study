static UWildcard create(Kind kind,@Nullable UTree<?> bound){
  checkArgument(BOUND_KINDS.containsKey(kind));
  checkArgument((bound == null) == (kind == Kind.UNBOUNDED_WILDCARD));
  return new AutoValue_UWildcard(kind,bound);
}
