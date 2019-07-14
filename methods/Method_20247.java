/** 
 * The entry point for setting up swipe support for a RecyclerView. The RecyclerView must be set with an Epoxy adapter or controller.
 */
public static SwipeBuilder initSwiping(RecyclerView recyclerView){
  return new SwipeBuilder(recyclerView);
}
