public static R<Boolean> rest(boolean result){
  R<Boolean> r=new R<Boolean>();
  if (!result) {
    r.setCode(R.FAIL);
    r.setData(false);
  }
  return r;
}
