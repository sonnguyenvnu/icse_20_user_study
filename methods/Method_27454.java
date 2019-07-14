private void setupLabels(@Nullable List<LabelModel> labelList){
  if (labelList != null && !labelList.isEmpty()) {
    SpannableBuilder builder=SpannableBuilder.builder();
    for (    LabelModel labelModel : labelList) {
      int color=Color.parseColor("#" + labelModel.getColor());
      builder.append(" ").append(" " + labelModel.getName() + " ",new LabelSpan(color));
    }
    labels.setText(builder);
    labelsHolder.setVisibility(View.VISIBLE);
  }
 else {
    labels.setText("");
    labelsHolder.setVisibility(View.GONE);
  }
}
