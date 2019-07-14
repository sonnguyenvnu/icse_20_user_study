private static void loadDetails(View dialogLayout,ThemedActivity activity,MediaDetailsMap<String,String> metadata){
  LinearLayout detailsTable=dialogLayout.findViewById(R.id.ll_list_details);
  int tenPxInDp=Measure.pxToDp(10,activity);
  int hundredPxInDp=Measure.pxToDp(125,activity);
  for (  int index : metadata.getKeySet()) {
    LinearLayout row=new LinearLayout(activity.getApplicationContext());
    row.setOrientation(LinearLayout.HORIZONTAL);
    TextView label=new TextView(activity.getApplicationContext());
    TextView value=new TextView(activity.getApplicationContext());
    label.setText(metadata.getLabel(index));
    label.setLayoutParams((new LinearLayout.LayoutParams(hundredPxInDp,LinearLayout.LayoutParams.WRAP_CONTENT)));
    value.setText(metadata.getValue(index));
    value.setLayoutParams((new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT)));
    label.setTextColor(activity.getTextColor());
    label.setTypeface(null,Typeface.BOLD);
    label.setGravity(Gravity.END);
    label.setTextSize(16);
    value.setTextColor(activity.getTextColor());
    value.setTextSize(16);
    value.setPaddingRelative(tenPxInDp,0,tenPxInDp,0);
    row.addView(label);
    row.addView(value);
    detailsTable.addView(row,new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
  }
}
