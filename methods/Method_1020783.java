public void reset(){
  myMenus.setSelected(keyPromoterSettings.isMenusEnabled());
  myToolbarButtons.setSelected(keyPromoterSettings.isToolbarButtonsEnabled());
  myToolWindowButtons.setSelected(keyPromoterSettings.isToolWindowButtonsEnabled());
  myEditorPopupButtons.setSelected(keyPromoterSettings.isEditorPopupEnabled());
  myAllButtons.setSelected(keyPromoterSettings.isAllButtonsEnabled());
  myShowKeyboardShortcutsOnly.setSelected(keyPromoterSettings.isShowKeyboardShortcutsOnly());
  myProposeToCreateShortcutCount.setValue(keyPromoterSettings.getProposeToCreateShortcutCount());
  myShowClickCount.setValue(keyPromoterSettings.getShowTipsClickCount());
}
