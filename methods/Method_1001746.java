public static <O>Failable<O> error(String error){
  return new Failable<O>(null,error);
}
