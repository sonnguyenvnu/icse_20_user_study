public static void main(String... args){
  OrderInfo orderInfo=new OrderInfo();
  orderInfo.setCustomerName("Joe Smith");
  orderInfo.setStreetAddress("1234 Main Street");
  ModelMapper modelMapper=new ModelMapper();
  Order order=modelMapper.map(orderInfo,Order.class);
  assertEquals(order.getCustomer().getName(),orderInfo.getCustomerName());
  assertEquals(order.getAddress().getStreet(),orderInfo.getStreetAddress());
}
