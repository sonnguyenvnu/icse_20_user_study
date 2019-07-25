public void bind(Order order){
  showOrderRelevantInfo(order);
  updateButtonsByStatus(order.getStatus());
}
