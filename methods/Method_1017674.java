private void insert(RoutingContext ctx,String sql,JsonArray attributes){
  jdbcClient.getConnection(res -> {
    if (res.succeeded()) {
      try (final SQLConnection connection=res.result()){
        connection.updateWithParams(sql,attributes,insert -> {
          final UpdateResult ur=insert.result();
          if (ur != null) {
            ctx.response().setStatusCode(201).end(Integer.toString(ur.getKeys().getInteger(0)));
          }
 else {
            ctx.response().setStatusCode(500).end(String.format("Connection to database couldn't be established: %s",res.cause()));
          }
        }
);
      }
     }
 else {
      ctx.response().setStatusCode(500).end(String.format("Connection to database couldn't be established: %s",res.cause()));
    }
  }
);
}
