/*
 * Copyright 2003-2013 JetBrains s.r.o.
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
package jetbrains.mps.editor.runtime.style;

/**
 * User: shatalin
 * Date: 1/14/13
 */
public final class SimpleStyleAttribute<T> extends AbstractStyleAttribute<T> {
  private T myDefaultValue;

  /*package*/ SimpleStyleAttribute(String name, T defaultValue, boolean register) {
    super(name, register);
    myDefaultValue = defaultValue;
  }

  public SimpleStyleAttribute(String name, T defaultValue) {
    this(name, defaultValue, false);
  }

  public SimpleStyleAttribute(String name) {
    this(name, null, false);
  }

  @Override
  public T combine(T parentValue, T currentValue) {
    if (currentValue != null) {
      return currentValue;
    }
    return myDefaultValue;
  }
}
