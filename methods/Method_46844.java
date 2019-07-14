@Override public void onItemClick(AdapterView<?> parent,View view,int position,long id){
  this.selectedColor=Utils.getColor(getContext(),getColorResAt(position));
  ((CheckableCircleView)view).setChecked(true);
  onColorSelected.onColorSelected(this.selectedColor);
}
