private void updateSelectedTintButton(boolean animated){
  int childCount=tintButtonsContainer.getChildCount();
  for (int a=0; a < childCount; a++) {
    View child=tintButtonsContainer.getChildAt(a);
    if (child instanceof RadioButton) {
      RadioButton radioButton=(RadioButton)child;
      int num=(Integer)radioButton.getTag();
      int color2=currentType == 0 ? tintShadowColors[num] : tintHighlighsColors[num];
      radioButton.setChecked(currentColor == color2,animated);
      radioButton.setColor(num == 0 ? 0xffffffff : (currentType == 0 ? tintShadowColors[num] : tintHighlighsColors[num]),num == 0 ? 0xffffffff : (currentType == 0 ? tintShadowColors[num] : tintHighlighsColors[num]));
    }
  }
}
