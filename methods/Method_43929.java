private void addToToString(StringBuilder sb,List<? extends Order> orders,String description){
  sb.append(description);
  sb.append(": ");
  if (orders.isEmpty()) {
    sb.append("None\n");
  }
 else {
    sb.append("\n");
    for (    Order order : orders) {
      sb.append("  [order=");
      sb.append(order.toString());
      sb.append("]\n");
    }
  }
}
