@OnClick({R.id.addAssignee,R.id.addLabels,R.id.addMilestone}) public void onViewClicked(View view){
switch (view.getId()) {
case R.id.addAssignee:
    AssigneesDialogFragment.newInstance(login,repoId,false).show(getSupportFragmentManager(),"AssigneesDialogFragment");
  break;
case R.id.addLabels:
LabelListModel labelModels=new LabelListModel();
labelModels.addAll(this.labelModels);
LabelsDialogFragment.newInstance(labelModels,repoId,login).show(getSupportFragmentManager(),"LabelsDialogFragment");
break;
case R.id.addMilestone:
MilestoneDialogFragment.newInstance(login,repoId).show(getSupportFragmentManager(),"MilestoneDialogFragment");
break;
}
}
