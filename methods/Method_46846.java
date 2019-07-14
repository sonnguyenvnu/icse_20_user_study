public ArrayList<Integer> getCheckedItemPositions(){
  ArrayList<Integer> checkedItemPositions=new ArrayList<>();
  for (int i=0; i < itemsChecked.length; i++) {
    if (itemsChecked[i]) {
      (checkedItemPositions).add(i);
    }
  }
  return checkedItemPositions;
}
