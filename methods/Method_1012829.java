public void log(SysLog sysLog){
  rabbitTemplate.convertAndSend(RabbitConfig.queueName,JSON.toJSONString(sysLog));
}
