private void init() throws IOException {
  ConnectionFactory factory=new ConnectionFactory();
  factory.setAutomaticRecoveryEnabled(true);
  factory.setHost(this.server);
  if (this.port > 0)   factory.setPort(this.port);
  if (this.username != null && this.username.length() > 0)   factory.setUsername(this.username);
  if (this.password != null && this.password.length() > 0)   factory.setPassword(this.password);
  try {
    this.connection=factory.newConnection();
    if (!this.connection.isOpen())     throw new IOException("no connection");
    this.channel=connection.createChannel();
    if (!this.channel.isOpen())     throw new IOException("no channel");
    this.queues=new ConcurrentHashMap<>();
  }
 catch (  TimeoutException e) {
    throw new IOException(e.getMessage());
  }
}
