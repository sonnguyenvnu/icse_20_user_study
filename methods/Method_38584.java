/** 
 * Configures Joy props before Joy is started.
 */
public JoddJoy withProps(final Consumer<JoyPropsConfig> propsConsumer){
  joyPropsConsumers.add(propsConsumer);
  return this;
}
