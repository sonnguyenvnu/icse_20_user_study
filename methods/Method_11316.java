public static void setNormalization(final Activity a,View rootView,final RxTextAutoZoom aText){
  if (!(rootView instanceof RxTextAutoZoom)) {
    rootView.setOnTouchListener(new View.OnTouchListener(){
      @Override public boolean onTouch(      View v,      MotionEvent event){
        hideSoftKeyboard(a);
        if (aText.getMinTextSize() != null && aText.getTextSize() < aText.getMinTextSize()) {
          aText.setText(aText.getText().toString().replace("\n",""));
        }
        return false;
      }
    }
);
  }
  if (rootView instanceof ViewGroup) {
    for (int i=0; i < ((ViewGroup)rootView).getChildCount(); i++) {
      View innerView=((ViewGroup)rootView).getChildAt(i);
      setNormalization(a,innerView,aText);
    }
  }
}
