@OnCreateInitialState public static <T>void onCreateInitialState(SectionContext c,StateValue<HashSet> blacklistState){
  blacklistState.set(new HashSet());
}
