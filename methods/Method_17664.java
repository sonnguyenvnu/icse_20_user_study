/** 
 * If this flag is set then yoga would diff the layout without legacy flag and would set a bool in YogaNode(mDoesLegacyStretchFlagAffectsLayout) with true if the layouts were different and false if not
 */
public void setShouldDiffLayoutWithoutLegacyStretchBehaviour(boolean shouldDiffLayoutWithoutLegacyStretchBehaviour){
  YogaNative.jni_YGConfigSetShouldDiffLayoutWithoutLegacyStretchBehaviour(mNativePointer,shouldDiffLayoutWithoutLegacyStretchBehaviour);
}
