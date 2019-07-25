public void initialize(){
  if (Activator.getDefault().getWorkbench() == null) {
    return;
  }
  PreferenceManager pm=Activator.getDefault().getWorkbench().getPreferenceManager();
  if (pm != null) {
    IPreferenceNode generalNode=pm.find("org.eclipse.ui.preferencePages.Workbench");
    if (generalNode != null) {
      generalNode.remove("org.eclipse.ui.preferencePages.Workspace");
      generalNode.remove("org.eclipse.ui.preferencePages.ContentTypes");
      IPreferenceNode editorsNode=generalNode.findSubNode("org.eclipse.ui.preferencePages.Editors");
      if (editorsNode != null) {
        editorsNode.remove("org.eclipse.ui.preferencePages.FileEditors");
        IPreferenceNode textEditorsNode=editorsNode.findSubNode("org.eclipse.ui.preferencePages.GeneralTextEditor");
        if (textEditorsNode != null) {
          textEditorsNode.remove("org.eclipse.ui.editors.preferencePages.Spelling");
          textEditorsNode.remove("org.eclipse.ui.editors.preferencePages.QuickDiff");
          textEditorsNode.remove("org.eclipse.ui.editors.preferencePages.LinkedModePreferencePage");
        }
      }
      generalNode.remove("org.eclipse.ui.preferencePages.Perspectives");
      generalNode.remove("org.eclipse.equinox.security.ui.category");
      IPreferenceNode appearanceNode=generalNode.findSubNode("org.eclipse.ui.preferencePages.Views");
      if (appearanceNode != null) {
        appearanceNode.remove("org.eclipse.ui.preferencePages.Decorators");
      }
    }
    pm.remove("org.eclipse.equinox.internal.p2.ui.sdk.ProvisioningPreferencePage");
    IPreferenceNode helpNode=pm.find("org.eclipse.help.ui.browsersPreferencePage");
    if (helpNode != null) {
      helpNode.remove("org.eclipse.help.ui.contentPreferencePage");
    }
  }
}
