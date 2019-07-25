public void change(int order,String event){
  Order o=jdbcTemplate.queryForObject("select id, state from orders where id = ?",new Object[]{order},new RowMapper<Order>(){
    public Order mapRow(    ResultSet rs,    int rowNum) throws SQLException {
      return new Order(rs.getInt("id"),rs.getString("state"));
    }
  }
);
  handler.handleEventWithStateReactively(MessageBuilder.withPayload(event).setHeader("order",order).build(),o.state).subscribe();
}
