/** 
 * @param id Target cell's id. Can not be null
 * @return
 * @since 3.0.0
 */
public BaseCell findCellById(String id){
  if (mGroupBasicAdapter != null && id != null) {
    List<BaseCell> cells=mGroupBasicAdapter.getComponents();
    for (int i=0, size=cells.size(); i < size; i++) {
      if (id.equals(cells.get(i).id)) {
        return cells.get(i);
      }
    }
  }
  return null;
}
