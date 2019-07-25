private void setup(OptionSet options,Properties properties){
  this.log_level=fetchOption("log_level",options,properties,null);
  this.maxwellMysql=parseMysqlConfig("",options,properties);
  this.replicationMysql=parseMysqlConfig("replication_",options,properties);
  this.schemaMysql=parseMysqlConfig("schema_",options,properties);
  this.gtidMode=fetchBooleanOption("gtid_mode",options,properties,System.getenv(GTID_MODE_ENV) != null);
  this.databaseName=fetchOption("schema_database",options,properties,"maxwell");
  this.maxwellMysql.database=this.databaseName;
  this.producerFactory=fetchProducerFactory(options,properties);
  this.producerType=fetchOption("producer",options,properties,"stdout");
  this.producerAckTimeout=fetchLongOption("producer_ack_timeout",options,properties,0L);
  this.bootstrapperType=fetchOption("bootstrapper",options,properties,"async");
  this.clientID=fetchOption("client_id",options,properties,"maxwell");
  this.replicaServerID=fetchLongOption("replica_server_id",options,properties,6379L);
  this.javascriptFile=fetchOption("javascript",options,properties,null);
  this.kafkaTopic=fetchOption("kafka_topic",options,properties,"maxwell");
  this.deadLetterTopic=fetchOption("dead_letter_topic",options,properties,null);
  this.kafkaKeyFormat=fetchOption("kafka_key_format",options,properties,"hash");
  this.kafkaPartitionKey=fetchOption("kafka_partition_by",options,properties,null);
  this.kafkaPartitionColumns=fetchOption("kafka_partition_columns",options,properties,null);
  this.kafkaPartitionFallback=fetchOption("kafka_partition_by_fallback",options,properties,null);
  this.kafkaPartitionHash=fetchOption("kafka_partition_hash",options,properties,"default");
  this.ddlKafkaTopic=fetchOption("ddl_kafka_topic",options,properties,this.kafkaTopic);
  this.pubsubProjectId=fetchOption("pubsub_project_id",options,properties,null);
  this.pubsubTopic=fetchOption("pubsub_topic",options,properties,"maxwell");
  this.ddlPubsubTopic=fetchOption("ddl_pubsub_topic",options,properties,this.pubsubTopic);
  this.rabbitmqHost=fetchOption("rabbitmq_host",options,properties,"localhost");
  this.rabbitmqPort=Integer.parseInt(fetchOption("rabbitmq_port",options,properties,"5672"));
  this.rabbitmqUser=fetchOption("rabbitmq_user",options,properties,"guest");
  this.rabbitmqPass=fetchOption("rabbitmq_pass",options,properties,"guest");
  this.rabbitmqVirtualHost=fetchOption("rabbitmq_virtual_host",options,properties,"/");
  this.rabbitmqExchange=fetchOption("rabbitmq_exchange",options,properties,"maxwell");
  this.rabbitmqExchangeType=fetchOption("rabbitmq_exchange_type",options,properties,"fanout");
  this.rabbitMqExchangeDurable=fetchBooleanOption("rabbitmq_exchange_durable",options,properties,false);
  this.rabbitMqExchangeAutoDelete=fetchBooleanOption("rabbitmq_exchange_autodelete",options,properties,false);
  this.rabbitmqRoutingKeyTemplate=fetchOption("rabbitmq_routing_key_template",options,properties,"%db%.%table%");
  this.rabbitmqMessagePersistent=fetchBooleanOption("rabbitmq_message_persistent",options,properties,false);
  this.rabbitmqDeclareExchange=fetchBooleanOption("rabbitmq_declare_exchange",options,properties,true);
  this.redisHost=fetchOption("redis_host",options,properties,"localhost");
  this.redisPort=Integer.parseInt(fetchOption("redis_port",options,properties,"6379"));
  this.redisAuth=fetchOption("redis_auth",options,properties,null);
  this.redisDatabase=Integer.parseInt(fetchOption("redis_database",options,properties,"0"));
  this.redisPubChannel=fetchOption("redis_pub_channel",options,properties,"maxwell");
  this.redisListKey=fetchOption("redis_list_key",options,properties,"maxwell");
  this.redisType=fetchOption("redis_type",options,properties,"pubsub");
  String kafkaBootstrapServers=fetchOption("kafka.bootstrap.servers",options,properties,null);
  if (kafkaBootstrapServers != null)   this.kafkaProperties.setProperty("bootstrap.servers",kafkaBootstrapServers);
  if (properties != null) {
    for (Enumeration<Object> e=properties.keys(); e.hasMoreElements(); ) {
      String k=(String)e.nextElement();
      if (k.startsWith("custom_producer.")) {
        this.customProducerProperties.setProperty(k.replace("custom_producer.",""),properties.getProperty(k));
      }
 else       if (k.startsWith("kafka.")) {
        if (k.equals("kafka.bootstrap.servers") && kafkaBootstrapServers != null)         continue;
        this.kafkaProperties.setProperty(k.replace("kafka.",""),properties.getProperty(k));
      }
    }
  }
  this.producerPartitionKey=fetchOption("producer_partition_by",options,properties,"database");
  this.producerPartitionColumns=fetchOption("producer_partition_columns",options,properties,null);
  this.producerPartitionFallback=fetchOption("producer_partition_by_fallback",options,properties,null);
  this.kinesisStream=fetchOption("kinesis_stream",options,properties,null);
  this.kinesisMd5Keys=fetchBooleanOption("kinesis_md5_keys",options,properties,false);
  this.sqsQueueUri=fetchOption("sqs_queue_uri",options,properties,null);
  this.outputFile=fetchOption("output_file",options,properties,null);
  this.metricsPrefix=fetchOption("metrics_prefix",options,properties,"MaxwellMetrics");
  this.metricsReportingType=fetchOption("metrics_type",options,properties,null);
  this.metricsSlf4jInterval=fetchLongOption("metrics_slf4j_interval",options,properties,60L);
  int port=Integer.parseInt(fetchOption("metrics_http_port",options,properties,"8080"));
  if (port != 8080) {
    LOGGER.warn("metrics_http_port is deprecated, please use http_port");
    this.httpPort=port;
  }
 else {
    this.httpPort=Integer.parseInt(fetchOption("http_port",options,properties,"8080"));
  }
  this.httpBindAddress=fetchOption("http_bind_address",options,properties,null);
  this.httpPathPrefix=fetchOption("http_path_prefix",options,properties,"/");
  if (!this.httpPathPrefix.startsWith("/")) {
    this.httpPathPrefix="/" + this.httpPathPrefix;
  }
  this.metricsDatadogType=fetchOption("metrics_datadog_type",options,properties,"udp");
  this.metricsDatadogTags=fetchOption("metrics_datadog_tags",options,properties,"");
  this.metricsDatadogAPIKey=fetchOption("metrics_datadog_apikey",options,properties,"");
  this.metricsDatadogHost=fetchOption("metrics_datadog_host",options,properties,"localhost");
  this.metricsDatadogPort=Integer.parseInt(fetchOption("metrics_datadog_port",options,properties,"8125"));
  this.metricsDatadogInterval=fetchLongOption("metrics_datadog_interval",options,properties,60L);
  this.metricsJvm=fetchBooleanOption("metrics_jvm",options,properties,false);
  this.diagnosticConfig=new MaxwellDiagnosticContext.Config();
  this.diagnosticConfig.enable=fetchBooleanOption("http_diagnostic",options,properties,false);
  this.diagnosticConfig.timeout=fetchLongOption("http_diagnostic_timeout",options,properties,10000L);
  this.includeDatabases=fetchOption("include_dbs",options,properties,null);
  this.excludeDatabases=fetchOption("exclude_dbs",options,properties,null);
  this.includeTables=fetchOption("include_tables",options,properties,null);
  this.excludeTables=fetchOption("exclude_tables",options,properties,null);
  this.blacklistDatabases=fetchOption("blacklist_dbs",options,properties,null);
  this.blacklistTables=fetchOption("blacklist_tables",options,properties,null);
  this.filterList=fetchOption("filter",options,properties,null);
  this.includeColumnValues=fetchOption("include_column_values",options,properties,null);
  if (options != null && options.has("init_position")) {
    String initPosition=(String)options.valueOf("init_position");
    String[] initPositionSplit=initPosition.split(":");
    if (initPositionSplit.length < 2)     usageForOptions("Invalid init_position: " + initPosition,"--init_position");
    Long pos=0L;
    try {
      pos=Long.valueOf(initPositionSplit[1]);
    }
 catch (    NumberFormatException e) {
      usageForOptions("Invalid init_position: " + initPosition,"--init_position");
    }
    Long lastHeartbeat=0L;
    if (initPositionSplit.length > 2) {
      try {
        lastHeartbeat=Long.valueOf(initPositionSplit[2]);
      }
 catch (      NumberFormatException e) {
        usageForOptions("Invalid init_position: " + initPosition,"--init_position");
      }
    }
    this.initPosition=new Position(new BinlogPosition(pos,initPositionSplit[0]),lastHeartbeat);
  }
  this.replayMode=fetchBooleanOption("replay",options,null,false);
  this.masterRecovery=fetchBooleanOption("master_recovery",options,properties,false);
  this.ignoreProducerError=fetchBooleanOption("ignore_producer_error",options,properties,true);
  this.recaptureSchema=fetchBooleanOption("recapture_schema",options,null,false);
  outputConfig.includesBinlogPosition=fetchBooleanOption("output_binlog_position",options,properties,false);
  outputConfig.includesGtidPosition=fetchBooleanOption("output_gtid_position",options,properties,false);
  outputConfig.includesCommitInfo=fetchBooleanOption("output_commit_info",options,properties,true);
  outputConfig.includesXOffset=fetchBooleanOption("output_xoffset",options,properties,true);
  outputConfig.includesNulls=fetchBooleanOption("output_nulls",options,properties,true);
  outputConfig.includesServerId=fetchBooleanOption("output_server_id",options,properties,false);
  outputConfig.includesThreadId=fetchBooleanOption("output_thread_id",options,properties,false);
  outputConfig.includesSchemaId=fetchBooleanOption("output_schema_id",options,properties,false);
  outputConfig.includesRowQuery=fetchBooleanOption("output_row_query",options,properties,false);
  outputConfig.includesPrimaryKeys=fetchBooleanOption("output_primary_keys",options,properties,false);
  outputConfig.includesPrimaryKeyColumns=fetchBooleanOption("output_primary_key_columns",options,properties,false);
  outputConfig.outputDDL=fetchBooleanOption("output_ddl",options,properties,false);
  outputConfig.zeroDatesAsNull=fetchBooleanOption("output_null_zerodates",options,properties,false);
  this.excludeColumns=fetchOption("exclude_columns",options,properties,null);
  String encryptionMode=fetchOption("encrypt",options,properties,"none");
switch (encryptionMode) {
case "none":
    outputConfig.encryptionMode=EncryptionMode.ENCRYPT_NONE;
  break;
case "data":
outputConfig.encryptionMode=EncryptionMode.ENCRYPT_DATA;
break;
case "all":
outputConfig.encryptionMode=EncryptionMode.ENCRYPT_ALL;
break;
default :
usage("Unknown encryption mode: " + encryptionMode);
break;
}
if (outputConfig.encryptionEnabled()) {
outputConfig.secretKey=fetchOption("secret_key",options,properties,null);
}
}
