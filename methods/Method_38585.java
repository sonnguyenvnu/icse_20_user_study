/** 
 * Configures the Joy proxetta.
 */
public JoddJoy withProxetta(final Consumer<JoyProxettaConfig> proxettaConsumer){
  joyProxettaConsumers.add(proxettaConsumer);
  return this;
}
