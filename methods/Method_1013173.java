@Override public void dispose(){
  final AssignmentWizard wizard=(AssignmentWizard)getWizard();
  if (wizard.getWizardDialogReturnCode() == org.eclipse.jface.window.Window.CANCEL) {
    super.dispose();
    return;
  }
  String rightSide=FormHelper.trimTrailingSpaces(source.getDocument().get());
  if (optionModelValue != null && optionSetModelValues != null) {
    this.getAssignment().setModelValue(optionModelValue.getSelection() || optionSetModelValues.getSelection());
    if (optionModelValue.getSelection()) {
      this.getAssignment().setRight(this.getAssignment().getLabel());
    }
 else     if (optionSetModelValues.getSelection()) {
      TypedSet set=TypedSet.parseSet(rightSide);
      this.getAssignment().setSymmetric(flagSymmetricalSet.getSelection());
      this.getAssignment().setRight(set.toString());
      set=TypedSet.parseSet(this.getAssignment().getRight());
      if (optionSMVUntyped.getSelection()) {
        final String type=smvTypeCombo.getText();
        set.setType(type);
      }
 else {
        set.unsetType();
      }
      this.getAssignment().setRight(set.toString());
    }
 else {
      this.getAssignment().setRight(rightSide);
    }
  }
 else   if (optionModelValue != null) {
    this.getAssignment().setModelValue(optionModelValue.getSelection());
    if (optionModelValue.getSelection()) {
      this.getAssignment().setRight(this.getAssignment().getLabel());
    }
 else {
      this.getAssignment().setRight(rightSide);
    }
  }
 else {
    this.getAssignment().setRight(rightSide);
  }
  if (paramComposite.hasParameters()) {
    this.getAssignment().setParams(paramComposite.getValues());
  }
  super.dispose();
}
