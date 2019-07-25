public static boolean hooked(Member member){
  return hookedInfo.containsKey(member) || entityMap.containsKey(member);
}
