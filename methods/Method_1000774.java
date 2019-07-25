protected void print(ActionInfo ai){
  if (log.isDebugEnabled()) {
    String[] paths=ai.getPaths();
    StringBuilder sb=new StringBuilder();
    if (null != paths && paths.length > 0) {
      sb.append("   '").append(paths[0]).append("'");
      for (int i=1; i < paths.length; i++)       sb.append(", '").append(paths[i]).append("'");
    }
 else {
      throw Lang.impossible();
    }
    Method method=ai.getMethod();
    String str;
    if (null != method)     str=genMethodDesc(ai);
 else     throw Lang.impossible();
    log.debugf("%s >> %50s | @Ok(%-5s) @Fail(%-5s) | by %d Filters | (I:%s/O:%s)",Strings.alignLeft(sb,30,' '),str,ai.getOkView(),ai.getFailView(),(null == ai.getFilterInfos() ? 0 : ai.getFilterInfos().length),ai.getInputEncoding(),ai.getOutputEncoding());
  }
}
