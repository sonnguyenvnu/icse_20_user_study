/** 
 * Create an array of index columns from a list of columns. The default sort type is used.
 * @param columns the column list
 * @return the index column array
 */
public static IndexColumn[] wrap(Column[] columns){
  IndexColumn[] list=new IndexColumn[columns.length];
  for (int i=0; i < list.length; i++) {
    list[i]=new IndexColumn();
    list[i].column=columns[i];
  }
  return list;
}
