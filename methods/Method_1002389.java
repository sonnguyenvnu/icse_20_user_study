private void usage() throws IOException {
  StringBuilder sb=new StringBuilder();
  sb.append("\nExamples");
  sb.append("\n========");
  sb.append("\nExample RunDiscovery (register clusters/services with zk): lb-client.sh -z zk://localhost:2121 -p /d2 -f d2_config_example.json -D");
  sb.append("\nExample RunDiscovery (register clusters/services with zk): lb-client.sh --zkserver zk://localhost:2121 --path /d2 --file d2_config_example.json --rundiscovery");
  sb.append("\nExample Print zk stores: lb-client.sh -z zk://localhost:2121 -p /d2 -c cluster-1 -s service-1_1 -S");
  sb.append("\nExample Print zk stores: lb-client.sh --zkserver zk://localhost:2121 --path /d2 --cluster cluster-1 --service service-1_1 --printstores");
  sb.append("\nExample Print single store: lb-client.sh -z=zk://localhost:2181 -p=/d2 -c='cluster-1' -s=service-1_1 -P");
  sb.append("\nExample Print single store: lb-client.sh --zkserver=zk://localhost:2181 --path=/d2 --cluster='cluster-1' --service=service-1_1 --printstore");
  sb.append("\nExample Delete store: lb-client.sh -z zk://localhost:2121 -p /d2 -d cluster-2 -n clusters");
  sb.append("\nExample Delete store: lb-client.sh -z zk://localhost:2121 -p /d2 -d service-3_3 -n services");
  sb.append("\nExample Delete store: lb-client.sh --zkserver zk://localhost:2121 --path /d2  --delete cluster-2 -storename clusters");
  sb.append("\nExample Print Service Schema: lb-client.sh -z zk://localhost:2121 -p /d2 -c 'cluster-1' -s service-1_1 -H");
  sb.append("\nExample Print Service Schema: lb-client.sh --zkserver zk://localhost:2181 --path /d2 --cluster 'cluster-1' --service service-1_1 --printschema");
  sb.append("\nExample Get Endpoints: lb-client.sh --zkserver zk://localhost:2121 --path /d2 --cluster cluster-1 --endpoints --service service-1_1");
  sb.append("\nExample Send request to service: lb-client.sh -z zk://localhost:2181 -p /d2 -c 'cluster-1' -s service-1_1 -r 'test' -R");
  sb.append("\nExample Send request to service: lb-client.sh -z zk://localhost:2181 -p /d2 -c 'cluster-1' -s service-1_1 -r 'test' -t rpc -R");
  sb.append("\nExample Send request to service: lb-client.sh --zkserver zk://localhost:2181 --path /d2 --cluster 'cluster-1' --service service-1_1 --request 'test' --sendrequest");
  sb.append("\nExample Send request to service: lb-client.sh -z zk://localhost:2181 -p /d2 -c 'history-write-1' -s HistoryService -m getCube -r 'test' -R");
  sb.append("\nExample Send request to service: lb-client.sh --zkserver zk://localhost:2181 --path /d2 --cluster 'history-write-1' --service HistoryService --method getCube --request 'test' --sendrequest");
  sb.append("\nExample Reset toggling stores: lb-client.sh -z zk://localhost:2121 -p /d2 -h localhost -b false -T");
  sb.append("\nExample Reset toggling stores: lb-client.sh --zkserver zk://localhost:2121 --path /d2 --host localhost --enabled false --toggle");
  sb.append("\n");
  final HelpFormatter formatter=new HelpFormatter();
  formatter.printHelp("lb-client.sh -z=zk://<zk host port> -p=<d2 path> ... parameters..." + sb.toString(),OPTIONS);
  System.exit(0);
}
