@RequestMapping("/oauth/uncache_approvals") @ResponseStatus(HttpStatus.NO_CONTENT) public void stopCaching() throws Exception {
  userApprovalHandler.setUseApprovalStore(false);
}
