@Override public void onSelectedLabels(@NonNull ArrayList<LabelModel> labelModels){
  this.labelModels.clear();
  this.labelModels.addAll(labelModels);
  SpannableBuilder builder=SpannableBuilder.builder();
  for (int i=0; i < labelModels.size(); i++) {
    LabelModel labelModel=labelModels.get(i);
    int color=Color.parseColor("#" + labelModel.getColor());
    if (i > 0) {
      builder.append(" ").append(" " + labelModel.getName() + " ",new LabelSpan(color));
    }
 else {
      builder.append(labelModel.getName() + " ",new LabelSpan(color));
    }
  }
  this.labels.setText(builder);
}
