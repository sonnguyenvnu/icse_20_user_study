@Override public AuthorizeResult authorize(){
  try {
    String[] perms=requiresPermissions.value();
    Subject subject=SecurityUtils.getSubject();
    if (perms.length == 1) {
      subject.checkPermission(perms[0]);
      return AuthorizeResult.ok();
    }
    if (Logical.AND.equals(requiresPermissions.logical())) {
      subject.checkPermissions(perms);
      return AuthorizeResult.ok();
    }
    if (Logical.OR.equals(requiresPermissions.logical())) {
      boolean hasAtLeastOnePermission=false;
      for (      String permission : perms)       if (subject.isPermitted(permission))       hasAtLeastOnePermission=true;
      if (!hasAtLeastOnePermission)       subject.checkPermission(perms[0]);
    }
    return AuthorizeResult.ok();
  }
 catch (  AuthorizationException e) {
    return AuthorizeResult.fail(AuthorizeResult.ERROR_CODE_UNAUTHORIZATION);
  }
}
