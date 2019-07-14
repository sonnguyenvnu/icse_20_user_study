@RequestMapping("/oauth/cache_approvals") @ResponseStatus(HttpStatus.NO_CONTENT) public void startCaching() throws Exception {
  userApprovalHandler.setUseApprovalStore(true);
}
