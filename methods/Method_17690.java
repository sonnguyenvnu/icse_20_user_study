@Override public boolean getDoesLegacyStretchFlagAffectsLayout(){
  return arr != null && (((int)arr[LAYOUT_EDGE_SET_FLAG_INDEX] & DOES_LEGACY_STRETCH_BEHAVIOUR) == DOES_LEGACY_STRETCH_BEHAVIOUR);
}
