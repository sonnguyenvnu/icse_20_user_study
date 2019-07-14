private void createLabels(){
  Context context=new ContextThemeWrapper(getContext(),mLabelsStyle);
  for (int i=0; i < mButtonsCount; i++) {
    FloatingActionButton button=(FloatingActionButton)getChildAt(i);
    String title=button.getTitle();
    if (button == mAddButton || title == null || button.getTag(R.id.fab_label) != null)     continue;
    TextView label=new TextView(context);
    label.setTextAppearance(getContext(),mLabelsStyle);
    label.setText(button.getTitle());
    addView(label);
    button.setTag(R.id.fab_label,label);
  }
}
