/** 
 * The entry point for setting up drag support.
 * @param controller The EpoxyController with the models that will be dragged. The controller willbe updated for you when a model is dragged and moved by a user's touch interaction.
 */
public static DragBuilder initDragging(EpoxyController controller){
  return new DragBuilder(controller);
}
