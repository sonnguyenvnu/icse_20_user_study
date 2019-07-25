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
package jetbrains.mps.workbench.index;

import com.intellij.util.io.DataExternalizer;
import jetbrains.mps.util.io.ModelInputStream;
import jetbrains.mps.util.io.ModelOutputStream;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SModelId;
import org.jetbrains.mps.openapi.model.SNodeId;
import org.jetbrains.mps.openapi.module.SModuleId;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Integrating {@link SNodeEntry} into IDEA's Index persistence mechanism.
 * Does not tolerate <code>null</code> values.
 *
 * @author Artem Tikhomirov
 */
public final class SNodeEntryExternalizer implements DataExternalizer<SNodeEntry> {
  private final int myMarkerToken;

  /**
   * @param useMarkerToken <code>true</code> if SNodeEntry shall be identified with a marker in the data output (writes/reads extra int)
   */
  public SNodeEntryExternalizer(boolean useMarkerToken) {
    myMarkerToken = useMarkerToken ? 0x110DEE11 : 0;
  }

  @Override
  public void save(@NotNull DataOutput out, SNodeEntry value) throws IOException {
    if (value == null) {
      throw new NullPointerException("Shall not try to serialize null values");
    }
    ByteArrayOutputStream bos = new ByteArrayOutputStream(17 /*Module UUID */ + 17 /*Model UUID*/ + 9 /*Node long*/);
    ModelOutputStream mos = new ModelOutputStream(bos);
    saveEntry(mos, value);
    mos.close();
    writeToken(out);
    byte[] bytes = bos.toByteArray();
    out.writeInt(bytes.length);
    out.write(bytes);
  }

  @Override
  public SNodeEntry read(@NotNull DataInput in) throws IOException {
    readToken(in);
    ModelInputStream mis = openStream(in);
    SNodeEntry rv = readEntry(mis);
    mis.close();
    return rv;
  }


  public void saveMany(@NotNull DataOutput out, @NotNull Collection<SNodeEntry> values) throws IOException {
    writeToken(out);
    ByteArrayOutputStream bos = new ByteArrayOutputStream(17 /*Module UUID */ + 17 /*Model UUID*/ + 9 /*Node long*/);
    ModelOutputStream mos = new ModelOutputStream(bos);
    for (SNodeEntry value : values) {
      saveEntry(mos, value);
    }
    mos.close();
    writeToken(out);
    byte[] bytes = bos.toByteArray();
    out.writeInt(bytes.length);
    out.write(bytes);
  }

  public List<SNodeEntry> readMany(@NotNull DataInput in) throws IOException {
    readToken(in);
    ArrayList<SNodeEntry> rv = new ArrayList<>();
    ModelInputStream mis = openStream(in);
    while (mis.available() > 0) {
      rv.add(readEntry(mis));
    }
    mis.close();
    return rv;

  }

  private void saveEntry(ModelOutputStream mos, SNodeEntry value) throws IOException {
    mos.writeModuleID(value.getModuleId());
    mos.writeModelID(value.getModelId());
    mos.writeNodeId(value.getNodeId());
  }

  private SNodeEntry readEntry(ModelInputStream mis) throws IOException {
    SModuleId module = mis.readModuleID();
    SModelId model = mis.readModelID();
    SNodeId node = mis.readNodeId();
    return new SNodeEntry(module, model, node);
  }

  private ModelInputStream openStream(DataInput in) throws IOException {
    int len = in.readInt();
    assert len > 0;
    byte[] data = new byte[len];
    in.readFully(data);
    return new ModelInputStream(new ByteArrayInputStream(data));
  }

  private void writeToken(DataOutput out) throws IOException {
    if (myMarkerToken != 0) {
      out.writeInt(myMarkerToken);
    }
  }

  private void readToken(DataInput in) throws IOException {
    if (myMarkerToken != 0) {
      int token = in.readInt();
      if (myMarkerToken != token) {
        throw new IOException("Bad stream, token to identify SNodeEntry expected.");
      }
    }
  }
}
