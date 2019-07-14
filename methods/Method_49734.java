private void addTextViewToLayout(String text){
  TextView textView=new TextView(Home.this);
  textView.setText(text);
  outputLayout.addView(textView);
}
