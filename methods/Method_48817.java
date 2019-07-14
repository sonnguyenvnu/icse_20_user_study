private static MessageScope normalizeScope(MessageScope scope){
  if (scope instanceof MessageScope.Global)   return GLOBAL_SCOPE;
 else   return scope;
}
