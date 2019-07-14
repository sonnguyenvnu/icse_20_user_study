/** 
 * Mark the object as clean every time it is serialized. This behaviour is not very clean - it is inherited from the previous deserialization code.
 * @return
 */
@JsonProperty("makeClean") @JsonInclude(Include.NON_NULL) public Integer markAsClean(){
  dirty=false;
  return null;
}
