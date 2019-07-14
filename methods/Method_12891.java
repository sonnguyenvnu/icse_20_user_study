/** 
 */
public void appendOneRow(List<String> row){
  for (int i=0; i < row.size(); i++) {
    List<String> list;
    if (head.size() <= i) {
      list=new ArrayList<String>();
      head.add(list);
    }
 else {
      list=head.get(0);
    }
    list.add(row.get(i));
  }
}
