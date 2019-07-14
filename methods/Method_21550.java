/** 
 * Get the types the query refer to.
 * @return list of strings, the types names
 */
public String[] getTypeArr(){
  List<String> list=new ArrayList<>();
  From index=null;
  for (int i=0; i < from.size(); i++) {
    index=from.get(i);
    if (index.getType() != null && index.getType().trim().length() > 0) {
      list.add(index.getType());
    }
  }
  if (list.size() == 0) {
    return null;
  }
  return list.toArray(new String[list.size()]);
}
