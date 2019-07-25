private void display(String title,String web){
  v.setTextHtml(web,"nosub");
  if (title != null && !title.isEmpty()) {
    ((Toolbar)findViewById(R.id.toolbar)).setTitle(title);
  }
 else {
    int index=v.getText().toString().indexOf("\n");
    if (index < 0) {
      index=0;
    }
    ((Toolbar)findViewById(R.id.toolbar)).setTitle(v.getText().toString().substring(0,index));
  }
}
