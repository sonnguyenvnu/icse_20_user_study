@OnClick({R.id.cancel,R.id.ok}) public void onViewClicked(View view){
switch (view.getId()) {
case R.id.ok:
    ActivityHelper.startCustomTab(getActivity(),"http://rebrand.ly/fasthub");
  break;
}
if (listener != null) listener.onDismissed();
dismiss();
}
