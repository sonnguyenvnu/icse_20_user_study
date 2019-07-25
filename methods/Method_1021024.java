@Override public AuthorizeResult authorize(){
  String[] roles=requiresRoles.value();
  try {
    if (roles.length == 1) {
      SecurityUtils.getSubject().checkRole(roles[0]);
      return AuthorizeResult.ok();
    }
    if (Logical.AND.equals(requiresRoles.logical())) {
      SecurityUtils.getSubject().checkRoles(Arrays.asList(roles));
      return AuthorizeResult.ok();
    }
    if (Logical.OR.equals(requiresRoles.logical())) {
      boolean hasAtLeastOneRole=false;
      for (      String role : roles)       if (SecurityUtils.getSubject().hasRole(role))       hasAtLeastOneRole=true;
      if (!hasAtLeastOneRole)       SecurityUtils.getSubject().checkRole(roles[0]);
    }
    return AuthorizeResult.ok();
  }
 catch (  AuthorizationException e) {
    return AuthorizeResult.fail(AuthorizeResult.ERROR_CODE_UNAUTHORIZATION);
  }
}
