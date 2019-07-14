public static Toast error(@NonNull String message,int duration,boolean withIcon){
  return custom(RxTool.getContext(),message,getDrawable(RxTool.getContext(),R.drawable.ic_clear_white_48dp),DEFAULT_TEXT_COLOR,ERROR_COLOR,duration,withIcon,true);
}
