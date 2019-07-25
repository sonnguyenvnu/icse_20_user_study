public static Transaction New(){
  return null == implClass ? new NutTransaction() : Mirror.me(implClass).born();
}
