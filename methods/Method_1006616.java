/** 
 * Initialization.
 */
private void init(){
  this.channel.attr(HEARTBEAT_COUNT).set(0);
  this.channel.attr(PROTOCOL).set(this.protocolCode);
  this.channel.attr(VERSION).set(this.version);
  this.channel.attr(HEARTBEAT_SWITCH).set(true);
}
