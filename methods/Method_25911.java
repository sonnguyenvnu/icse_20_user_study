private static boolean isWhitelistedInterfaceType(Type type,VisitorState state){
  return WHITELISTED_TYPES.stream().map(state::getTypeFromString).anyMatch(whitelistedType -> ASTHelpers.isSubtype(type,whitelistedType,state));
}
