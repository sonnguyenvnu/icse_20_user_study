private void runInstant(){
  System.out.println("Instant");
  System.out.println("=======");
  System.out.println("Instant stores a point in the datetime continuum as millisecs from 1970-01-01T00:00:00Z");
  System.out.println("Instant is immutable and thread-safe");
  System.out.println("                      in = new Instant()");
  Instant in=new Instant();
  System.out.println("Millisecond time:     in.getMillis():           " + in.getMillis());
  System.out.println("ISO string version:   in.toString():            " + in.toString());
  System.out.println("ISO chronology:       in.getChronology():       " + in.getChronology());
  System.out.println("UTC time zone:        in.getDateTimeZone():     " + in.getZone());
  System.out.println("Change millis:        in.withMillis(0):         " + in.withMillis(0L));
  System.out.println("");
  System.out.println("Convert to Instant:   in.toInstant():           " + in.toInstant());
  System.out.println("Convert to DateTime:  in.toDateTime():          " + in.toDateTime());
  System.out.println("Convert to MutableDT: in.toMutableDateTime():   " + in.toMutableDateTime());
  System.out.println("Convert to Date:      in.toDate():              " + in.toDate());
  System.out.println("");
  System.out.println("                      in2 = new Instant(in.getMillis() + 10)");
  Instant in2=new Instant(in.getMillis() + 10);
  System.out.println("Equals ms and chrono: in.equals(in2):           " + in.equals(in2));
  System.out.println("Compare millisecond:  in.compareTo(in2):        " + in.compareTo(in2));
  System.out.println("Compare millisecond:  in.isEqual(in2):          " + in.isEqual(in2));
  System.out.println("Compare millisecond:  in.isAfter(in2):          " + in.isAfter(in2));
  System.out.println("Compare millisecond:  in.isBefore(in2):         " + in.isBefore(in2));
}
