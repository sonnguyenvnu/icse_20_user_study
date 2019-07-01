protected boolean _XXXXX_(DavServletRequest request,String repositoryId) throws DavException {
  try {
    AuthenticationResult result=httpAuth.getAuthenticationResult(request,null);
    SecuritySession securitySession=httpAuth.getSecuritySession(request.getSession(true));
    return servletAuth.isAuthenticated(request,result) && servletAuth.isAuthorized(request,securitySession,repositoryId,WebdavMethodUtil.getMethodPermission(request.getMethod()));
  }
 catch (  AuthenticationException e) {
    String guest=UserManager.GUEST_USERNAME;
    try {
      if (servletAuth.isAuthorized(guest,((ArchivaDavResourceLocator)request.getRequestLocator()).getRepositoryId(),WebdavMethodUtil.getMethodPermission(request.getMethod()))) {
        return true;
      }
    }
 catch (    UnauthorizedException ae) {
      throw new UnauthorizedDavException(repositoryId,"You are not authenticated and authorized to access any repository.");
    }
    throw new UnauthorizedDavException(repositoryId,"You are not authenticated");
  }
catch (  MustChangePasswordException e) {
    throw new UnauthorizedDavException(repositoryId,"You must change your password.");
  }
catch (  AccountLockedException e) {
    throw new UnauthorizedDavException(repositoryId,"User account is locked.");
  }
catch (  AuthorizationException e) {
    throw new DavException(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Fatal Authorization Subsystem Error.");
  }
catch (  UnauthorizedException e) {
    throw new UnauthorizedDavException(repositoryId,e.getMessage());
  }
}