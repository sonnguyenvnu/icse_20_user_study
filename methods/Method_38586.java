/** 
 * Configures the Joy petite.
 */
public JoddJoy withPetite(final Consumer<JoyPetiteConfig> petiteConsumer){
  joyPetiteConsumers.add(petiteConsumer);
  return this;
}
