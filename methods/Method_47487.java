public void invoke(FilterInvocation fi) throws IOException, ServletException {
  InterceptorStatusToken token=super.beforeInvocation(fi);
  try {
    fi.getChain().doFilter(fi.getRequest(),fi.getResponse());
  }
  finally {
    super.afterInvocation(token,null);
  }
}
