public static Command create(SubjectPattern subject,VerbPattern verb,ComplementPattern complement){
  final RegexConcat pattern;
  if (complement instanceof ComplementEmpty) {
    pattern=new RegexConcat(RegexLeaf.start(),subject.toRegex(),RegexLeaf.spaceOneOrMore(),verb.toRegex(),RegexLeaf.end());
  }
 else {
    pattern=new RegexConcat(RegexLeaf.start(),subject.toRegex(),RegexLeaf.spaceOneOrMore(),verb.toRegex(),RegexLeaf.spaceOneOrMore(),complement.toRegex("0"),RegexLeaf.end());
  }
  return new NaturalCommand(pattern,subject,verb,complement);
}
