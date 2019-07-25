/*
 * Copyright 2003-2018 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jetbrains.mps.references;


/**
 * This class stores methods that represent every java operation with every possible overloading
 *
 * TODO implement every overloading
 * TODO maybe worth to automatically generate
 * TODO do we need handle cases like 'int += long'??? In java, it is desugared to 'i = (int) (i + l)'
 */
public class BLOperations {

  public static <T> T assign(Reference<T> l, T r) {
    l.set(r);
    return r;
  }
  public static int assign(Reference<Integer> l, int r) {
    l.set(r);
    return r;
  }
  public static long assign(Reference<Long> l, long r) {
    l.set(r);
    return r;
  }
  public static short assign(Reference<Short> l, short r) {
    l.set(r);
    return r;
  }
  public static boolean assign(Reference<Boolean> l, boolean r) {
    l.set(r);
    return r;
  }
  public static float assign(Reference<Float> l, float r) {
    l.set(r);
    return r;
  }
  public static double assign(Reference<Double> l, double r) {
    l.set(r);
    return r;
  }
  public static byte assign(Reference<Byte> l, byte r) {
    l.set(r);
    return r;
  }
  public static char assign(Reference<Character> l, char r) {
    l.set(r);
    return r;
  }


  public static int plusAssign(Reference<Integer> l, int r) {
    return assign(l, l.get() + r);
  }
  public static long plusAssign(Reference<Long> l, long r) {
    return assign(l, l.get() + r);
  }
  public static float plusAssign(Reference<Float> l, float r) {
    return assign(l, l.get() + r);
  }
  public static double plusAssign(Reference<Double> l, double r) {
    return assign(l, l.get() + r);
  }
  public static byte plusAssign(Reference<Byte> l, byte r) {
    return assign(l, (byte) (l.get() + r));
  }
  public static short plusAssign(Reference<Short> l, short r) {
    return assign(l, (short) (l.get() + r));
  }
  public static char plusAssign(Reference<Character> l, char r) {
    return assign(l, (char) (l.get() + r));
  }
  public static String plusAssign(Reference<String> l, Object r) {
    return assign(l, l.get() + r);
  }

  public static int minusAssign(Reference<Integer> l, int r) {
    return assign(l, l.get() - r);
  }
  public static long minusAssign(Reference<Long> l, long r) {
    return assign(l, l.get() - r);
  }
  public static float minusAssign(Reference<Float> l, float r) {
    return assign(l, l.get() - r);
  }
  public static double minusAssign(Reference<Double> l, double r) {
    return assign(l, l.get() - r);
  }
  public static byte minusAssign(Reference<Byte> l, byte r) {
    return assign(l, (byte) (l.get() - r));
  }
  public static short minusAssign(Reference<Short> l, short r) {
    return assign(l, (short) (l.get() - r));
  }
  public static char minusAssign(Reference<Character> l, char r) {
    return assign(l, (char) (l.get() - r));
  }

  public static int multAssign(Reference<Integer> l, int r) {
    return assign(l, l.get() * r);
  }
  public static long multAssign(Reference<Long> l, long r) {
    return assign(l, l.get() * r);
  }
  public static float multAssign(Reference<Float> l, float r) {
    return assign(l, l.get() * r);
  }
  public static double multAssign(Reference<Double> l, double r) {
    return assign(l, l.get() * r);
  }
  public static byte multAssign(Reference<Byte> l, byte r) {
    return assign(l, (byte) (l.get() * r));
  }
  public static short multAssign(Reference<Short> l, short r) {
    return assign(l, (short) (l.get() * r));
  }
  public static char multAssign(Reference<Character> l, char r) {
    return assign(l, (char) (l.get() * r));
  }

  public static int divAssign(Reference<Integer> l, int r) {
    return assign(l, l.get() / r);
  }
  public static long divAssign(Reference<Long> l, long r) {
    return assign(l, l.get() / r);
  }
  public static float divAssign(Reference<Float> l, float r) {
    return assign(l, l.get() / r);
  }
  public static double divAssign(Reference<Double> l, double r) {
    return assign(l, l.get() / r);
  }
  public static byte divAssign(Reference<Byte> l, byte r) {
    return assign(l, (byte) (l.get() / r));
  }
  public static short divAssign(Reference<Short> l, short r) {
    return assign(l, (short) (l.get() / r));
  }
  public static char divAssign(Reference<Character> l, char r) {
    return assign(l, (char) (l.get() / r));
  }

  public static int remAssign(Reference<Integer> l, int r) {
    return assign(l, l.get() % r);
  }
  public static long remAssign(Reference<Long> l, long r) {
    return assign(l, l.get() % r);
  }
  public static float remAssign(Reference<Float> l, float r) {
    return assign(l, l.get() % r);
  }
  public static double remAssign(Reference<Double> l, double r) {
    return assign(l, l.get() % r);
  }
  public static byte remAssign(Reference<Byte> l, byte r) {
    return assign(l, (byte) (l.get() % r));
  }
  public static short remAssign(Reference<Short> l, short r) {
    return assign(l, (short) (l.get() % r));
  }
  public static char remAssign(Reference<Character> l, char r) {
    return assign(l, (char) (l.get() % r));
  }


  public static boolean andAssign(Reference<Boolean> l, boolean r) {
    return assign(l, l.get() && r);
  }
  public static int andAssign(Reference<Integer> l, int r) {
    return assign(l, l.get() & r);
  }

  public static boolean orAssign(Reference<Boolean> l, boolean r) {
    return assign(l, l.get() || r);
  }
  public static int orAssign(Reference<Integer> l, int r) {
    return assign(l, l.get() | r);
  }

  public static boolean xorAssign(Reference<Boolean> l, boolean r) {
    return assign(l, l.get() ^ r);
  }
  public static int xorAssign(Reference<Integer> l, int r) {
    return assign(l, l.get() ^ r);
  }

  public static int shiftLeftAssign(Reference<Integer> l, int r) {
    return assign(l, l.get() << r);
  }
  public static int shiftRightAssign(Reference<Integer> l, int r) {
    return assign(l, l.get() >> r);
  }
  public static int shiftRightUAssign(Reference<Integer> l, int r) {
    return assign(l, l.get() >>> r);
  }


  public static int incrementAndGet_int(Reference<Integer> v) {
    int r = v.get() + 1;
    v.set(r);
    return r;
  }
  public static long incrementAndGet_long(Reference<Long> v) {
    long r = v.get() + 1;
    v.set(r);
    return r;
  }
  public static float incrementAndGet_float(Reference<Float> v) {
    float r = v.get() + 1;
    v.set(r);
    return r;
  }
  public static double incrementAndGet_double(Reference<Double> v) {
    double r = v.get() + 1;
    v.set(r);
    return r;
  }
  public static byte incrementAndGet_byte(Reference<Byte> v) {
    byte r = (byte) (v.get() + 1);
    v.set(r);
    return r;
  }
  public static short incrementAndGet_short(Reference<Short> v) {
    short r = (short) (v.get() + 1);
    v.set(r);
    return r;
  }
  public static char incrementAndGet_char(Reference<Character> v) {
    char r = (char) (v.get() + 1);
    v.set(r);
    return r;
  }

  public static int getAndIncrement_int(Reference<Integer> v) {
    int r = v.get();
    v.set(r + 1);
    return v.get();
  }
  public static long getAndIncrement_long(Reference<Long> v) {
    long r = v.get();
    v.set(r + 1);
    return r;
  }
  public static float getAndIncrement_float(Reference<Float> v) {
    float r = v.get();
    v.set(r + 1);
    return r;
  }
  public static double getAndIncrement_double(Reference<Double> v) {
    double r = v.get();
    v.set(r + 1);
    return r;
  }
  public static byte getAndIncrement_byte(Reference<Byte> v) {
    byte r = v.get();
    v.set((byte) (r + 1));
    return r;
  }
  public static short getAndIncrement_short(Reference<Short> v) {
    short r = v.get();
    v.set((short) (r + 1));
    return r;
  }
  public static char getAndIncrement_char(Reference<Character> v) {
    char r = v.get();
    v.set((char) (r + 1));
    return r;
  }

  public static int decrementAndGet_int(Reference<Integer> v) {
    int r = v.get() - 1;
    v.set(r);
    return r;
  }
  public static long decrementAndGet_long(Reference<Long> v) {
    long r = v.get() - 1;
    v.set(r);
    return r;
  }
  public static float decrementAndGet_float(Reference<Float> v) {
    float r = v.get() - 1;
    v.set(r);
    return r;
  }
  public static double decrementAndGet_double(Reference<Double> v) {
    double r = v.get() - 1;
    v.set(r);
    return r;
  }
  public static byte decrementAndGet_byte(Reference<Byte> v) {
    byte r = (byte) (v.get() - 1);
    v.set(r);
    return r;
  }
  public static short decrementAndGet_short(Reference<Short> v) {
    short r = (short) (v.get() - 1);
    v.set(r);
    return r;
  }
  public static char decrementAndGet_char(Reference<Character> v) {
    char r = (char) (v.get() - 1);
    v.set(r);
    return r;
  }

  public static int getAndDecrement_int(Reference<Integer> v) {
    int r = v.get();
    v.set(r - 1);
    return v.get();
  }
  public static long getAndDecrement_long(Reference<Long> v) {
    long r = v.get();
    v.set(r - 1);
    return r;
  }
  public static float getAndDecrement_float(Reference<Float> v) {
    float r = v.get();
    v.set(r - 1);
    return r;
  }
  public static double getAndDecrement_double(Reference<Double> v) {
    double r = v.get();
    v.set(r - 1);
    return r;
  }
  public static byte getAndDecrement_byte(Reference<Byte> v) {
    byte r = v.get();
    v.set((byte) (r - 1));
    return r;
  }
  public static short getAndDecrement_short(Reference<Short> v) {
    short r = v.get();
    v.set((short) (r - 1));
    return r;
  }
  public static char getAndDecrement_char(Reference<Character> v) {
    char r = v.get();
    v.set((char) (r - 1));
    return r;
  }

}
