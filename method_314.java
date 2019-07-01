@Override public void _XXXXX_(DataOutput out) throws IOException {
  out.writeLong(this.startTimestamp);
  out.writeLong(this.stopTimestamp);
  keyValues.write(out);
}