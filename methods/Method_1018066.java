@Override public ExecutionContext authenticate(Credentials credentials,String repositoryName,String workspaceName,ExecutionContext repositoryContext,Map<String,Object> sessionAttributes){
  if (credentials instanceof OverrideCredentials) {
    return repositoryContext.with(new AdminSecurityContext());
  }
 else {
    return null;
  }
}
