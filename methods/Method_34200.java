@JsonIgnore public boolean isApproved(){
  return isCurrentlyActive() && status == ApprovalStatus.APPROVED;
}
