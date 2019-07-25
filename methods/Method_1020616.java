/** 
 * Create a  {@link MobiusLoop.Controller} that allows you to start, stop, and restart MobiusLoops.
 * @param loopFactory a factory for creating loops
 * @param defaultModel the model the controller should start from
 * @return a new controller
 */
public static <M,E,F>MobiusLoop.Controller<M,E> controller(MobiusLoop.Factory<M,E,F> loopFactory,M defaultModel){
  return new MobiusLoopController<>(loopFactory,defaultModel,WorkRunners.immediate());
}
