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
 * Represents java array element variable
 */
public class ArrayElementReference<T> implements Reference<T> {
  private final T[] myArray;
  private final int myIndex;

  public ArrayElementReference(T[] array, int index) {
    myArray = array;
    myIndex = index;
  }

  @Override
  public T get() {
    return myArray[myIndex];
  }
  @Override
  public void set(T value) {
    myArray[myIndex] = value;
  }

  public static class _int implements Reference<Integer> {
    private final int[] myArray;
    private final int myIndex;

    public _int(int[] array, int index) {
      myArray = array;
      myIndex = index;
    }

    @Override
    public Integer get() {
      return myArray[myIndex];
    }
    @Override
    public void set(Integer value) {
      myArray[myIndex] = value;
    }
  }

  public static class _short implements Reference<Short> {
    private final short[] myArray;
    private final int myIndex;

    public _short(short[] array, int index) {
      myArray = array;
      myIndex = index;
    }

    @Override
    public Short get() {
      return myArray[myIndex];
    }
    @Override
    public void set(Short value) {
      myArray[myIndex] = value;
    }
  }

  public static class _long implements Reference<Long> {
    private final long[] myArray;
    private final int myIndex;

    public _long(long[] array, int index) {
      myArray = array;
      myIndex = index;
    }

    @Override
    public Long get() {
      return myArray[myIndex];
    }
    @Override
    public void set(Long value) {
      myArray[myIndex] = value;
    }
  }

  public static class _float implements Reference<Float> {
    private final float[] myArray;
    private final int myIndex;

    public _float(float[] array, int index) {
      myArray = array;
      myIndex = index;
    }

    @Override
    public Float get() {
      return myArray[myIndex];
    }
    @Override
    public void set(Float value) {
      myArray[myIndex] = value;
    }
  }

  public static class _double implements Reference<Double> {
    private final double[] myArray;
    private final int myIndex;

    public _double(double[] array, int index) {
      myArray = array;
      myIndex = index;
    }

    @Override
    public Double get() {
      return myArray[myIndex];
    }
    @Override
    public void set(Double value) {
      myArray[myIndex] = value;
    }
  }

  public static class _char implements Reference<Character> {
    private final char[] myArray;
    private final int myIndex;

    public _char(char[] array, int index) {
      myArray = array;
      myIndex = index;
    }

    @Override
    public Character get() {
      return myArray[myIndex];
    }
    @Override
    public void set(Character value) {
      myArray[myIndex] = value;
    }
  }

  public static class _byte implements Reference<Byte> {
    private final byte[] myArray;
    private final int myIndex;

    public _byte(byte[] array, int index) {
      myArray = array;
      myIndex = index;
    }

    @Override
    public Byte get() {
      return myArray[myIndex];
    }
    @Override
    public void set(Byte value) {
      myArray[myIndex] = value;
    }
  }

  public static class _boolean implements Reference<Boolean> {
    private final boolean[] myArray;
    private final int myIndex;

    public _boolean(boolean[] array, int index) {
      myArray = array;
      myIndex = index;
    }

    @Override
    public Boolean get() {
      return myArray[myIndex];
    }
    @Override
    public void set(Boolean value) {
      myArray[myIndex] = value;
    }
  }

  public static <T> Reference<T> create(T[] array, int index) {
    return new ArrayElementReference(array, index);
  }
  public static Reference<Integer> create(int[] array, int index) {
    return new ArrayElementReference._int(array, index);
  }
  public static Reference<Long> create(long[] array, int index) {
    return new ArrayElementReference._long(array, index);
  }
  public static Reference<Byte> create(byte[] array, int index) {
    return new ArrayElementReference._byte(array, index);
  }
  public static Reference<Short> create(short[] array, int index) {
    return new ArrayElementReference._short(array, index);
  }
  public static Reference<Character> create(char[] array, int index) {
    return new ArrayElementReference._char(array, index);
  }
  public static Reference<Boolean> create(boolean[] array, int index) {
    return new ArrayElementReference._boolean(array, index);
  }
  public static Reference<Float> create(float[] array, int index) {
    return new ArrayElementReference._float(array, index);
  }
  public static Reference<Double> create(double[] array, int index) {
    return new ArrayElementReference._double(array, index);
  }
}
