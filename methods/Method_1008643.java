public static void write(TDigestState state,StreamOutput out) throws IOException {
  out.writeDouble(state.compression);
  out.writeVInt(state.centroidCount());
  for (  Centroid centroid : state.centroids()) {
    out.writeDouble(centroid.mean());
    out.writeVLong(centroid.count());
  }
}
