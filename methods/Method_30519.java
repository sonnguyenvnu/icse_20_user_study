public CharSequence getContentWithEntities(){
  return TextEntity.applyEntities(content,entities);
}
