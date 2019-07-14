void showUrlSelection(){
  String text=getCurrentTermSession().getEmulator().getScreen().getTranscriptText();
  LinkedHashSet<CharSequence> urlSet=extractUrls(text);
  if (urlSet.isEmpty()) {
    new AlertDialog.Builder(this).setMessage(R.string.select_url_no_found).show();
    return;
  }
  final CharSequence[] urls=urlSet.toArray(new CharSequence[0]);
  Collections.reverse(Arrays.asList(urls));
  final AlertDialog dialog=new AlertDialog.Builder(TermuxActivity.this).setItems(urls,(di,which) -> {
    String url=(String)urls[which];
    ClipboardManager clipboard=(ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
    clipboard.setPrimaryClip(new ClipData(null,new String[]{"text/plain"},new ClipData.Item(url)));
    Toast.makeText(TermuxActivity.this,R.string.select_url_copied_to_clipboard,Toast.LENGTH_LONG).show();
  }
).setTitle(R.string.select_url_dialog_title).create();
  dialog.setOnShowListener(di -> {
    ListView lv=dialog.getListView();
    lv.setOnItemLongClickListener((parent,view,position,id) -> {
      dialog.dismiss();
      String url=(String)urls[position];
      Intent i=new Intent(Intent.ACTION_VIEW,Uri.parse(url));
      try {
        startActivity(i,null);
      }
 catch (      ActivityNotFoundException e) {
        startActivity(Intent.createChooser(i,null));
      }
      return true;
    }
);
  }
);
  dialog.show();
}
