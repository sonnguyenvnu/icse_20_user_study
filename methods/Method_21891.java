@OnClick(R.id.get_current_date) public void getCurrentDatesClick(final View v){
  Toast.makeText(this,widget.getCurrentDate().toString(),Toast.LENGTH_SHORT).show();
  Log.e("GettersActivity",widget.getCurrentDate().toString());
}
