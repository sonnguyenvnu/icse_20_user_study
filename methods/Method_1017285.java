@Override public void run(Race.Registration registration) throws Exception {
  try (JMSContext jmsContext=cf.createContext()){
    final TemporaryQueue responseQueue=jmsContext.createTemporaryQueue();
    final String request=UUID.randomUUID().toString();
    jmsContext.createProducer().setJMSReplyTo(responseQueue).send(requestQueue,request);
    try (JMSConsumer consumer=jmsContext.createConsumer(responseQueue)){
      String response=consumer.receiveBody(String.class);
      if (response == null) {
        registration.aborted(new IllegalStateException("Message processing timed out"));
      }
 else       if (!response.equals(request)) {
        registration.aborted(new IllegalStateException("Response content does not match the request. Response: " + response + ", request: " + request));
      }
    }
   }
 }
