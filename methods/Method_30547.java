public CharSequence getTextWithEntities(boolean appendParent,Context context){
  CharSequence textWithEntities=TextEntity.applyEntities(text,entities);
  if (appendParent) {
    if (textWithEntities == null) {
      textWithEntities="";
    }
    textWithEntities=appendParentText(textWithEntities,parentBroadcast,parentBroadcastId,context);
  }
  return textWithEntities;
}
