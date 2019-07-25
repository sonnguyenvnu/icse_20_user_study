public Summary summarize(){
  return Summary.create(trace(),errors(),ResultGroup.summarize(groups()),statistics(),limits(),dataDensity().orElse(Histogram.empty()));
}
