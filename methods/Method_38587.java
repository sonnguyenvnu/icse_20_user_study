/** 
 * Configures the Joy db.
 */
public JoddJoy withDb(final Consumer<JoyDbConfig> dbConsumer){
  joyDbConsumers.add(dbConsumer);
  return this;
}
