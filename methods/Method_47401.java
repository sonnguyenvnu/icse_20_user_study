@Override public void onPostExecute(Void v){
  if (!targetShareIntents.isEmpty()) {
    MaterialDialog.Builder builder=new MaterialDialog.Builder(contextc);
    builder.title(R.string.share);
    builder.theme(appTheme.getMaterialDialogTheme());
    ShareAdapter shareAdapter=new ShareAdapter(contextc,targetShareIntents,arrayList1,arrayList2);
    builder.adapter(shareAdapter,null);
    builder.negativeText(R.string.cancel);
    builder.negativeColor(fab_skin);
    MaterialDialog b=builder.build();
    shareAdapter.updateMatDialog(b);
    b.show();
  }
 else {
    Toast.makeText(contextc,R.string.noappfound,Toast.LENGTH_SHORT).show();
  }
}
