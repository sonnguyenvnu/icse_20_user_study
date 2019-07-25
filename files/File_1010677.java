/*
 * Copyright 2003-2016 JetBrains s.r.o.
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
package jetbrains.mps.util;

import java.util.function.Function;

/**
 * Mutable pair of values
 * @param <T1> type of first value
 * @param <T2> type of second value
 */
@SuppressWarnings({"InstanceVariableNamingConvention"})
public final class Pair<T1, T2> {
  public T1 o1;
  public T2 o2;

  public Pair(T1 o1, T2 o2) {
    this.o1 = o1;
    this.o2 = o2;
  }

  public int hashCode() {
    int result = 0;
    if (o1 != null) result += o1.hashCode();
    if (o2 != null) result += o2.hashCode() * 37;
    return result;
  }

  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof Pair)) return false;
    Pair pair = (Pair) obj;
    return equalsWithNull(pair.o1, o1) && equalsWithNull(pair.o2, o2);
  }

  private static boolean equalsWithNull(Object o1, Object o2) {
    if (o1 == null && o2 == null) return true;
    if (o1 != null && o2 != null) return o1.equals(o2);
    return false;
  }

  public String toString() {
    return "(" + o1 + ", " + o2 + ")";
  }

  /**
   * @return Function that extracts {@linkplain #o1 first value} from a pair
   */
  public static <P1,P2> Function<Pair<P1, P2>, P1> first() {
    return p -> p.o1;
  }

  /**
   * @return Function that extracts {@linkplain #o2 second value} from a pair
   */
  public static <P1,P2> Function<Pair<P1, P2>, P2> second() {
    return p -> p.o2;
  }
}
