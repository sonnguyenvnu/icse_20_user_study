/** 
 * this property is used to replace JavaFX maximization with a custom one that prevents hiding windows taskbar when the JFXDecorator is maximized.
 * @return customMaximizeProperty whether to use custom maximization or not.
 */
public final BooleanProperty customMaximizeProperty(){
  return this.customMaximize;
}
