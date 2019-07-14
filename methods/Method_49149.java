private void verifyOpen(){
  if (isClosed())   throw new IllegalStateException("Operation cannot be executed because the enclosing transaction is closed");
}
