public void setProjectStateView(){
switch (this.project.state()) {
case Project.STATE_SUCCESSFUL:
    this.percentageFundedProgressBar.setVisibility(View.GONE);
  this.projectStateViewGroup.setVisibility(View.VISIBLE);
this.fundingUnsuccessfulTextView.setVisibility(View.GONE);
this.successfullyFundedTextView.setVisibility(View.VISIBLE);
this.successfullyFundedTextView.setText(this.successfulString);
break;
case Project.STATE_CANCELED:
this.percentageFundedProgressBar.setVisibility(View.GONE);
this.projectStateViewGroup.setVisibility(View.VISIBLE);
this.successfullyFundedTextView.setVisibility(View.GONE);
this.fundingUnsuccessfulTextView.setVisibility(View.VISIBLE);
this.fundingUnsuccessfulTextView.setText(this.cancelledString);
break;
case Project.STATE_FAILED:
this.percentageFundedProgressBar.setVisibility(View.GONE);
this.projectStateViewGroup.setVisibility(View.VISIBLE);
this.successfullyFundedTextView.setVisibility(View.GONE);
this.fundingUnsuccessfulTextView.setVisibility(View.VISIBLE);
this.fundingUnsuccessfulTextView.setText(this.unsuccessfulString);
break;
case Project.STATE_SUSPENDED:
this.percentageFundedProgressBar.setVisibility(View.GONE);
this.projectStateViewGroup.setVisibility(View.VISIBLE);
this.successfullyFundedTextView.setVisibility(View.GONE);
this.fundingUnsuccessfulTextView.setVisibility(View.VISIBLE);
this.fundingUnsuccessfulTextView.setText(this.suspendedString);
break;
default :
this.percentageFundedProgressBar.setVisibility(View.VISIBLE);
this.projectStateViewGroup.setVisibility(View.GONE);
break;
}
}
