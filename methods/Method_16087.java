public Proxy<I> custom(Consumer<CtClass> ctClassConsumer){
  ctClassConsumer.accept(ctClass);
  return this;
}
