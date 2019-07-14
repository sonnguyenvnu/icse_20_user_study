private void postUnMountView(BaseCell cell,View view){
  if (view instanceof ITangramViewLifeCycle) {
    ((ITangramViewLifeCycle)view).postUnBindView(cell);
  }
 else {
    if (postUnBindMap.get(cell) != null) {
      try {
        postUnBindMap.get(cell).invoke(view,cell);
      }
 catch (      Exception e) {
        e.printStackTrace();
      }
    }
  }
}
