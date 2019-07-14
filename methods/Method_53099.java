void init(JSONObject json) throws TwitterException {
  this.limit=ParseUtil.getInt("limit",json);
  this.remaining=ParseUtil.getInt("remaining",json);
  this.resetTimeInSeconds=ParseUtil.getInt("reset",json);
  this.secondsUntilReset=(int)((resetTimeInSeconds * 1000L - System.currentTimeMillis()) / 1000);
}
