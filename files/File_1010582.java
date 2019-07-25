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
package jetbrains.mps.smodel.adapter.ids;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.annotations.Immutable;

import java.util.UUID;

@Immutable
public final class SLanguageId {
  private final long myHigh;
  private final long myLow;

  public SLanguageId(@NotNull UUID id) {
    this(id.getMostSignificantBits(), id.getLeastSignificantBits());
  }

  public SLanguageId(long mostSignificantBits, long leastSignificantBits) {
    myHigh = mostSignificantBits;
    myLow = leastSignificantBits;
  }

  @NotNull
  public UUID getIdValue() {
    return new UUID(myHigh, myLow);
  }

  public long getHighBits() {
    return myHigh;
  }

  public long getLowBits() {
    return myLow;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    SLanguageId that = (SLanguageId) o;

    return myHigh == that.myHigh && myLow == that.myLow;
  }

  @Override
  public int hashCode() {
    // kudos to UUID.hashCode()
    return (int)((myHigh >> 32) ^ myHigh ^ (myLow >> 32) ^ myLow);
  }

  public String serialize(){
    return getIdValue().toString();
  }

  public static SLanguageId deserialize(String s){
    return new SLanguageId(UUID.fromString(s));
  }

  @Override
  public String toString() {
    return serialize();
  }
}
