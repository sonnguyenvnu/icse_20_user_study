/** 
 * Syncronize pipeline by reading all responses. This operation closes the pipeline. In order to get return values from pipelined commands, capture the different Response&lt;?&gt; of the commands you execute.
 */
public void sync(){
  for (  Client client : clients) {
    generateResponse(client.getOne());
  }
}
