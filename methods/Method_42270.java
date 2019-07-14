private void setupData(@NonNull Context context,@NonNull AttributeSet attributeSet){
  TypedArray typedArray=context.obtainStyledAttributes(attributeSet,R.styleable.NavigationEntry);
  String displayText=typedArray.getString(R.styleable.NavigationEntry_itemText);
  String displayIcon=typedArray.getString(R.styleable.NavigationEntry_itemIcon);
  typedArray.recycle();
  setText(displayText);
  if (displayIcon == null)   return;
  setIcon(displayIcon);
}
