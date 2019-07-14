/** 
 * @param param tree item
 * @return the data represented by the tree item
 */
public final ObservableValue<T> getComputedValue(CellDataFeatures<S,T> param){
  Object rowObject=param.getValue().getValue();
  if (rowObject instanceof RecursiveTreeObject) {
    RecursiveTreeObject<?> item=(RecursiveTreeObject<?>)rowObject;
    if (item.getGroupedColumn() == this) {
      return new ReadOnlyObjectWrapper(item.getGroupedValue());
    }
  }
  return null;
}
