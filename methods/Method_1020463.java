private static void instantiate(Type type){
  if (type.isInstantiated()) {
    return;
  }
  type.instantiate();
  type.getPotentiallyLiveMembers().forEach(RapidTypeAnalyser::markMemberLive);
}
