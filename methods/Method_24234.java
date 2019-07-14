@Override public int clientWaitSync(long sync,int flags,long timeout){
  if (gl3es3 != null) {
    return gl3es3.glClientWaitSync(sync,flags,timeout);
  }
 else {
    throw new RuntimeException(String.format(MISSING_GLFUNC_ERROR,"clientWaitSync()"));
  }
}
