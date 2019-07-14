/** 
 * register cell with custom model class and view creator
 * @param type
 * @param cellClz
 * @param viewHolderCreator
 * @param < V >
 */
public <V extends View>void registerCell(String type,@NonNull Class<? extends BaseCell> cellClz,@NonNull ViewHolderCreator viewHolderCreator){
  viewHolderMap.put(type,viewHolderCreator);
  registerCell(type,cellClz,viewHolderCreator.viewClz);
}
