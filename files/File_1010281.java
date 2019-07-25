/*
 * Copyright 2003-2019 JetBrains s.r.o.
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
package jetbrains.mps.generator.cache;

import jetbrains.mps.vfs.IFile;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Facility to mimic try-with-resource clause, to ensure streams are closed once parse is complete
 *
 * @author Artem Tikhomirov
 */
public final class ParseFacility<T> {

  private final Class<?> myOwner;
  private final Parser<T> myParser;
  private IFile myFile;
  private URL myUrl;

  public ParseFacility(Class<?> owner, @NotNull Parser<T> parser) {
    myOwner = owner;
    myParser = parser;
  }

  public ParseFacility<T> input(IFile file) {
    myFile = file;
    myUrl = null;
    return this;
  }

  public ParseFacility<T> input(URL url) {
    myFile = null;
    myUrl = url;
    return this;
  }

  public boolean isValidInput() {
    if (myFile != null) {
      return myFile.exists() && myFile.length() > 0;
    }
    return myUrl != null;
  }

  /**
   * Parse and ignore errors, if any
   *
   * @return <code>null</code> if didn't succeed
   */
  public T parseSilently() {
    if (!isValidInput()) {
      return null;
    }
    try {
      return parse();
    } catch (FileNotFoundException ex) {
      // ok, just ignore
    } catch (IOException ex) {
      getLog().warn(String.format("Ignored parse error in %s",  myFile == null ? myUrl : myFile));
    }
    return null;
  }

  public T parse() throws IOException {
    InputStream is = null;
    try {
      // XXX perhaps, shall wrap is with a BufferedInputStream (any that supports mark()), and check if stream is not empty
      //     to avoid garbage 'Premature end of file' warnings in the log. Makes sense even for IFile streams (regardless of length check in isValidInput
      //     as its content perhaps may be different due to vfs caching).
      is = openStream();
      return myParser.load(is);
    } finally {
      closeSilently(is);
    }
  }

  private InputStream openStream() throws IOException {
    assert isValidInput();
    if (myFile != null) {
      return myFile.openInputStream();
    }
    return myUrl.openStream();
  }

  private void closeSilently(InputStream is) {
    if (is == null) {
      return;
    }
    try {
      is.close();
    } catch (IOException ex) {
      getLog().error("Failed to close stream", ex);
    }
  }

  private Logger getLog() {
    return LogManager.getLogger(myOwner);
  }


  public interface Parser<T> {
    T load(InputStream is) throws IOException;
  }
}
