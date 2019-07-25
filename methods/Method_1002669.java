/** 
 * Creates a  {@link BackoffSpec} from a string.
 */
static BackoffSpec parse(String specification){
  requireNonNull(specification,"specification");
  final BackoffSpec spec=new BackoffSpec(specification);
  for (  String option : OPTION_SPLITTER.split(specification)) {
    spec.parseOption(option);
  }
  return spec;
}
