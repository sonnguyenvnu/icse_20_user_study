@Override public void customize(EditorMenuItemStyle customization,EditorMenuItemCustomizationContext context){
  if (context.get(CompletionMenuItemCustomizationContext.COMPLETION_ITEM_INFORMATION) == null) {
    return;
  }
  OldVarMacroStrikeout.OldVarMacroStrikeoutSpecific customizer=new OldVarMacroStrikeout.OldVarMacroStrikeoutSpecific();
  if (customizer.matches(context)) {
    customizer.customize(customization,context);
  }
}
