public static <M,E,F>MobiusLoop.Controller<M,E> controller(MobiusLoop.Factory<M,E,F> loopFactory,M defaultModel){
  return Mobius.controller(loopFactory,defaultModel,MainThreadWorkRunner.create());
}
