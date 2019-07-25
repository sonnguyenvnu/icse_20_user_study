public static double voronoi(RValue seed,RValue x,RValue y,RValue z,RValue frequency) throws EvaluationException {
  VoronoiNoise voronoi=localVoronoi.get();
  try {
    voronoi.setSeed((int)seed.getValue());
    voronoi.setFrequency(frequency.getValue());
  }
 catch (  IllegalArgumentException e) {
    throw new EvaluationException(0,"Voronoi error: " + e.getMessage());
  }
  return voronoi.noise(Vector3.at(x.getValue(),y.getValue(),z.getValue()));
}
