@Override public void onClick(View view){
switch (view.getId()) {
case R.id.shopping_cart_bottom:
case R.id.shopping_cart_layout:
    this.dismiss();
  break;
case R.id.clear_layout:
clear();
break;
}
}
