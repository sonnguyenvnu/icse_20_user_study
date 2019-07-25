public void destroy(){
  if (picker != null) {
    picker.dismiss();
  }
  picker=null;
  cb=null;
  milstd_search_cancel=null;
  milstd_search_results=null;
  milstd_search=null;
}
