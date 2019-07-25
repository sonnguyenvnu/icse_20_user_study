/*
 * Copyright 2003-2014 JetBrains s.r.o.
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
package jetbrains.mps.persistence.registry;

/**
 * we use text index for xml files, and ordering/integer index for binary files.
 * Index values either persisted or initialized by intrinsic ordering of meta-model information saved in the model
 */
/*package*/ abstract class BaseInfo {
  private String myIndex;
  private int myIntIndex = -1;

  public String getIndex() {
    assert myIndex != null;
    return myIndex;
  }

  public int getIntIndex() {
    return myIntIndex;
  }
  public void setIntIndex(int index) {
    myIntIndex = index;
  }

  /*package*/ void setIndex(String index) {
    myIndex = index;
  }

  /*package*/ abstract int internalKey();

  // long to signed integer
  protected static final int ltoi(long l) {
    return ((int) (l ^ (l>>>32)));
  }
  // FIXME drop this method (make ltoi to return unsigned value). Meanwhile, use non-negative values for comparator purposes only - I'd like to keep
  // models mostly unchanged to check merge
  protected static final int unsigned(int i) {
    return i & 0x7fffffff;
  }
}
