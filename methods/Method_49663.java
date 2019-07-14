private void openShareChooser(String code){
  Intent intent=new Intent(Intent.ACTION_SEND);
  intent.putExtra(Intent.EXTRA_TEXT,code);
  intent.setType("text/plain");
  mView.getContext().startActivity(Intent.createChooser(intent,mView.getContext().getResources().getText(R.string.share_chooser_title)));
}
