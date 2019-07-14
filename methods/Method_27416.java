public static void htmlIntoTextView(@NonNull TextView textView,@NonNull String html,int width){
  registerClickEvent(textView);
  textView.setText(initHtml(textView,width).fromHtml(format(html).toString()));
}
