/** 
 * Display a dialog prompting the user to pick a favorite food from a list, then record the answer.
 */
private void askFavoriteFood(){
  final String[] choices=getResources().getStringArray(R.array.food_items);
  AlertDialog ad=new AlertDialog.Builder(this).setCancelable(false).setTitle(R.string.food_dialog_title).setItems(choices,new DialogInterface.OnClickListener(){
    @Override public void onClick(    DialogInterface dialog,    int which){
      String food=choices[which];
      setUserFavoriteFood(food);
    }
  }
).create();
  ad.show();
}
