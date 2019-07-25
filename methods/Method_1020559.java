private void arg(String name,Object[] arr){
  if (arr == null) {
    arg(name,"null");
  }
 else {
    arg(name,toString(arr.length,arr,0));
  }
}
