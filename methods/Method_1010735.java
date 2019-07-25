@Override public JButton visit(SubMenu subMenu){
  PopupStep subStep=myStep.onChosen(subMenu,false);
  if (!(subStep instanceof ListPopupStep)) {
    throw new IllegalStateException("sub-step for " + subMenu + " must be a ListPopupStep but was " + subStep);
  }
  return new StepComboBoxButton(subMenu,(ListPopupStep<?>)subStep);
}
