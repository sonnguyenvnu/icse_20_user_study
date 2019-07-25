public void destroy(){
  if (picker != null) {
    picker.dismiss();
  }
  picker=null;
  milstd_search_cancel=null;
  milstd_search=null;
}
