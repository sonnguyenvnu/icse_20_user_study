public static void setMdText(@NonNull TextView textView,String markdown){
  if (!InputHelper.isEmpty(markdown)) {
    int width=textView.getMeasuredWidth();
    if (width > 0) {
      render(textView,markdown,width);
    }
 else {
      textView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener(){
        @Override public boolean onPreDraw(){
          textView.getViewTreeObserver().removeOnPreDrawListener(this);
          render(textView,markdown,textView.getMeasuredWidth());
          return true;
        }
      }
);
    }
  }
}
