public static double perlin(RValue seed,RValue x,RValue y,RValue z,RValue frequency,RValue octaves,RValue persistence) throws EvaluationException {
  PerlinNoise perlin=localPerlin.get();
  try {
    perlin.setSeed((int)seed.getValue());
    perlin.setFrequency(frequency.getValue());
    perlin.setOctaveCount((int)octaves.getValue());
    perlin.setPersistence(persistence.getValue());
  }
 catch (  IllegalArgumentException e) {
    throw new EvaluationException(0,"Perlin noise error: " + e.getMessage());
  }
  return perlin.noise(Vector3.at(x.getValue(),y.getValue(),z.getValue()));
}
