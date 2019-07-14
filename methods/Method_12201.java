@Override @Compensable(confirmMethod="confirmSendMessage",cancelMethod="cancelSendMessage",transactionContextEditor=DubboTransactionContextEditor.class) public String sendMessage(String message){
  System.out.println("this is sendMessage try message=" + message);
  if (message.equals("123")) {
    throw new NullPointerException();
  }
  return "quickstart-provider-message=" + message;
}
