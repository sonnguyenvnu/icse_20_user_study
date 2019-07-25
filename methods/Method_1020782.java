public void apply(){
  keyPromoterSettings.setMenusEnabled(myMenus.isSelected());
  keyPromoterSettings.setToolbarButtonsEnabled(myToolbarButtons.isSelected());
  keyPromoterSettings.setToolWindowButtonsEnabled(myToolWindowButtons.isSelected());
  keyPromoterSettings.setEditorPopupEnabled(myEditorPopupButtons.isSelected());
  keyPromoterSettings.setAllButtonsEnabled(myAllButtons.isSelected());
  keyPromoterSettings.setShowKeyboardShortcutsOnly(myShowKeyboardShortcutsOnly.isSelected());
  keyPromoterSettings.setProposeToCreateShortcutCount(new Integer(myProposeToCreateShortcutCount.getValue().toString()));
  keyPromoterSettings.setShowTipsClickCount(new Integer(myShowClickCount.getValue().toString()));
}
