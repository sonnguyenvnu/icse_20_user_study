private View inflateItem(LinearLayout container,final int index,int accentColor){
  View.OnClickListener clickListener=v -> {
    if (!v.isSelected()) {
      select(selectedItem,false);
      select(v,true);
      selectedItem=v;
      selectedIndex=index;
    }
  }
;
  LayoutInflater inflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  View child=inflater.inflate(R.layout.item_colorpicker,container,false);
  child.setOnClickListener(clickListener);
  RadioButton radio=child.findViewById(R.id.select);
  radio.setOnClickListener(clickListener);
  if (Build.VERSION.SDK_INT >= 21) {
    ColorStateList colorStateList=new ColorStateList(new int[][]{{-android.R.attr.state_enabled},{android.R.attr.state_enabled}},new int[]{accentColor,accentColor});
    radio.setButtonTintList(colorStateList);
  }
  return child;
}
