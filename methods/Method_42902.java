private String handleWithdrawalResponse(ANXWithdrawalResponseWrapper wrapper){
  ANXWithdrawalResponse response=wrapper.getAnxWithdrawalResponse();
  if (wrapper.getResult().equals("error")) {
    throw new IllegalStateException("Failed to withdraw funds: " + response.getMessage());
  }
 else   if (wrapper.getError() != null) {
    throw new IllegalStateException("Failed to withdraw funds: " + wrapper.getError());
  }
 else {
    return response.getTransactionId();
  }
}
