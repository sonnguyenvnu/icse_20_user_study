public static void bind(boolean compute,ExecController execController){
  STORAGE.set(new ExecThreadBinding(Thread.currentThread(),compute,execController));
}
