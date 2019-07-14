public static void setMdText(@NonNull TextView textView,String markdown,int width){
  if (!InputHelper.isEmpty(markdown)) {
    render(textView,markdown,width);
  }
}
