public void filter(final InterceptorChain chain) throws Throwable {
  try {
    Trans.begin(level);
    chain.doChain();
    Trans.commit();
  }
 catch (  Throwable e) {
    Trans.rollback();
    throw e;
  }
 finally {
    Trans.close();
  }
}
