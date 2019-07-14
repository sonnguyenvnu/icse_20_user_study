/** 
 * The implementing class of the  {@link HystrixCommand}.
 * @return {@code Class<? extends HystrixCommand> }
 */
public Class<? extends HystrixInvokable> getImplementingClass(){
  return commandClass;
}
