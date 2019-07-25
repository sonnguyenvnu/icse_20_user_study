/** 
 * Validates and builds a  {@link KafkaItemWriter}.
 * @return a {@link KafkaItemWriter}
 */
public KafkaItemWriter<K,V> build(){
  Assert.notNull(this.kafkaTemplate,"kafkaTemplate is required.");
  Assert.notNull(this.itemKeyMapper,"itemKeyMapper is required.");
  KafkaItemWriter<K,V> writer=new KafkaItemWriter<>();
  writer.setKafkaTemplate(this.kafkaTemplate);
  writer.setItemKeyMapper(this.itemKeyMapper);
  writer.setDelete(this.delete);
  return writer;
}
