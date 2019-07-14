@JsonProperty("status") public String getStatus(){
  return _done ? "done" : "pending";
}
