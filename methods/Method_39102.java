/** 
 * Binds chunk to an action runtime.
 */
public void bind(final ActionRuntime actionRuntime){
  this.actionRuntime=actionRuntime;
  this.actionRuntime.bind(this);
}
