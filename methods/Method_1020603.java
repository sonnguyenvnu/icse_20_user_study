public void open(int partitionId){
  _currPartitionIndex=partitionId;
  _connections=new DynamicPartitionConnections(_kafkaconfig,new ZkBrokerReader(_kafkaconfig,_state));
  _coordinator=new ZkCoordinator(_connections,_kafkaconfig,_state,partitionId,_receiver,true,_messageHandler);
}
