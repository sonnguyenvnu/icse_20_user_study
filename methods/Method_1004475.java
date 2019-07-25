boolean match(final SubEnvIsolationPullFilter filter,final PullRequest request,final Buffer result){
  final ByteBuffer message=result.getBuffer();
  message.mark();
  try {
    skipUntilBody(message);
    return isEnvMatch(filter,request,readBody(message));
  }
 catch (  Exception e) {
    LOG.error("check env match failed.",e);
    return false;
  }
 finally {
    message.reset();
  }
}
