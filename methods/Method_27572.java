@OnClick({R.id.add,R.id.search}) public void onViewClicked(View view){
switch (view.getId()) {
case R.id.add:
    listener.onAddSelected();
  break;
case R.id.search:
listener.onSearchSelected();
break;
}
dismiss();
}
