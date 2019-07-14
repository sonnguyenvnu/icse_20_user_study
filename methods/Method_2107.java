private void setUriIntent(final Button button,final String url){
  button.setOnClickListener(new View.OnClickListener(){
    @Override public void onClick(    View v){
      final Intent i=new Intent(Intent.ACTION_VIEW);
      i.setData(Uri.parse(url));
      startActivity(i);
    }
  }
);
}
