public void autoresize(){
  int items=getAdapter().getCount();
  int columns=items == 1 ? 1 : 2;
  setNumColumns(columns);
}
