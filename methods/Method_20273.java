@NonNull private static String buildMessage(EpoxyModel model,String descriptionOfWhenChangeHappened,int modelPosition){
  return new StringBuilder(descriptionOfWhenChangeHappened).append(" Position: ").append(modelPosition).append(" Model: ").append(model.toString()).append("\n\n").append(MODEL_CANNOT_BE_CHANGED_MESSAGE).toString();
}
