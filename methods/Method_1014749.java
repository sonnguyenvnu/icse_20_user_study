public void demo(){
  orderRepository.createIfNotExistsTable();
  orderItemRepository.createIfNotExistsTable();
  orderRepository.truncateTable();
  orderItemRepository.truncateTable();
  List<Long> orderIds=new ArrayList<Long>(10);
  System.out.println("1.Insert--------------");
  for (int i=0; i < 10; i++) {
    Order order=new Order();
    order.setUserId(51);
    order.setStatus("INSERT_TEST");
    orderRepository.insert(order);
    long orderId=order.getOrderId();
    orderIds.add(orderId);
    OrderItem item=new OrderItem();
    item.setOrderId(orderId);
    item.setUserId(51);
    item.setStatus("INSERT_TEST");
    orderItemRepository.insert(item);
  }
  System.out.println(orderItemRepository.selectAll());
  System.out.println("2.Delete--------------");
  for (  Long each : orderIds) {
    orderRepository.delete(each);
    orderItemRepository.delete(each);
  }
  System.out.println(orderItemRepository.selectAll());
  orderItemRepository.dropTable();
  orderRepository.dropTable();
}
