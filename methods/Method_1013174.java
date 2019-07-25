public void dispose(){
  if (isCurrentPage() && optionTyped.getSelection()) {
    String type=typeCombo.getText();
    Assignment assignment=((AssignmentWizard)getWizard()).getFormula();
    TypedSet set=TypedSet.parseSet(assignment.getRight());
    set.setType(type);
    assignment.setRight(set.toString());
  }
  super.dispose();
}
