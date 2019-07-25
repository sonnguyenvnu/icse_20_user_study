public static void main(String[] args){
  throw new UndeclaredThrowableException(new PrivilegedActionException(new ScriptException("Test exception for MPS-11085")));
}
