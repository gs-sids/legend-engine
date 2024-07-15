// Copyright 2020 Goldman Sachs
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package org.finos.legend.pure.runtime.java.extension.external.json.shared;

import org.finos.legend.pure.m3.coreinstance.meta.pure.metamodel.type.Enumeration;
import org.finos.legend.pure.m3.exception.PureExecutionException;
import org.finos.legend.pure.m4.coreinstance.CoreInstance;
import org.finos.legend.pure.runtime.java.extension.external.shared.conversion.ConversionContext;
import org.finos.legend.pure.runtime.java.extension.external.shared.conversion.EnumerationConversion;

public class JsonEnumerationDeserialization<T extends CoreInstance> extends EnumerationConversion<String, T>
{
    public JsonEnumerationDeserialization(Enumeration<T> enumeration)
    {
        super(enumeration);
    }

    @Override
    public T apply(String jsonValue, ConversionContext context)
    {
        int dotIndex = jsonValue.lastIndexOf('.');
        String enumName = dotIndex == -1 ? jsonValue : jsonValue.substring(dotIndex + 1);
        T value = (T) this.valueMap.get(enumName);
        if (value == null)
        {
            throw new PureExecutionException(context.getSourceInformation(), "Unknown enum: " + this.enumerationName() + "." + enumName);
        }
        return value;
    }
}
