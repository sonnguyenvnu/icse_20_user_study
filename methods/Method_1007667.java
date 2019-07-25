/** 
 * Create a clone of this event with the given stage.
 * @param stage the stage
 * @return a new event
 */
public EditSessionEvent clone(Stage stage){
  return new EditSessionEvent(world,actor,maxBlocks,stage);
}
