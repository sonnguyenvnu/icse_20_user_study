/** 
 * Copies all the attributes of the specified habit into this habit
 * @param model the model whose attributes should be copied from
 */
public synchronized void copyFrom(@NonNull Habit model){
  this.data=new HabitData(model.data);
  observable.notifyListeners();
}
