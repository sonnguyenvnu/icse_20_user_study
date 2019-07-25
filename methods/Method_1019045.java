/** 
 * @return New scene with {@link #content()} sized to {@link #width()} and{@link #height()}.
 */
default Scene scene(){
  return JavaFX.scene(content(),width(),height());
}
