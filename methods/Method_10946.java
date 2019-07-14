public static void setEdDecimal(EditText editText,int count){
  if (count < 0) {
    count=0;
  }
  count+=1;
  editText.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);
  final int finalCount=count;
  editText.setFilters(new InputFilter[]{new InputFilter(){
    @Override public CharSequence filter(    CharSequence source,    int start,    int end,    Spanned dest,    int dstart,    int dend){
      if (".".contentEquals(source) && dest.toString().length() == 0) {
        return "0.";
      }
      if (dest.toString().contains(".")) {
        int index=dest.toString().indexOf(".");
        int mlength=dest.toString().substring(index).length();
        if (mlength == finalCount) {
          return "";
        }
      }
      if (dest.toString().equals("0") && source.equals("0")) {
        return "";
      }
      return null;
    }
  }
});
}
