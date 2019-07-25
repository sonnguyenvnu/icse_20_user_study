private void initialize(Context context){
  setKey(context.getResources().getString(R.string.server_choice_key));
  setPersistent(true);
  setDialogLayoutResource(R.layout.servers);
  summaryTemplate=context.getResources().getString(R.string.server_choice_summary);
  defaultDomain=context.getResources().getString(R.string.domain0);
  setPositiveButtonText(R.string.intro_accept);
}
