@KafkaListener(topics={"test"}) public void consume(String message){
  System.out.println(message);
}
