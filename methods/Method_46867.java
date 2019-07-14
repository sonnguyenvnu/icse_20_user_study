public ArrayList<LayoutElementParcelable> getCheckedItems(){
  ArrayList<LayoutElementParcelable> selected=new ArrayList<>();
  for (int i=0; i < itemsDigested.size(); i++) {
    if (itemsDigested.get(i).getChecked() == ListItem.CHECKED) {
      selected.add(itemsDigested.get(i).elem);
    }
  }
  return selected;
}
