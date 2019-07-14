protected Vertex findUser(final String username){
  credentials.tx().rollback();
  final GraphTraversal<Vertex,Vertex> t=credentials.users(username);
  final Vertex v=t.hasNext() ? t.next() : null;
  if (t.hasNext())   throw new IllegalStateException(String.format("Multiple users with username %s",username));
  return v;
}
