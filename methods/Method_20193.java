/** 
 * An optimized way to move a model from one position to another without rebuilding all models. This is intended to be used with  {@link androidx.recyclerview.widget.ItemTouchHelper} toallow for efficient item dragging and rearranging. It cannot be <p> If you call this you MUST also update the data backing your models as necessary. <p> This will immediately change the model's position and notify the change to the RecyclerView. However, a delayed request to rebuild models will be scheduled for the future to guarantee that models are in sync with data.
 * @param fromPosition Previous position of the item.
 * @param toPosition   New position of the item.
 */
public void moveModel(int fromPosition,int toPosition){
  assertNotBuildingModels();
  adapter.moveModel(fromPosition,toPosition);
  requestDelayedModelBuild(500);
}
