public void register(String cellId,BaseCell cell,View view){
  mvMap.put(cell,view);
  vmMap.put(view,cell);
  idViewMap.put(cellId,view);
}
