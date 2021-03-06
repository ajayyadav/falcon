/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.falcon;

import org.apache.falcon.entity.v0.Entity;
import org.apache.falcon.entity.v0.EntityType;
import org.apache.falcon.entity.v0.feed.Schema;
import org.apache.falcon.extensions.ExtensionBuilder;

import javax.xml.bind.JAXBException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Extension Example for testing extension loading and preparing entities.
 */
public class ExtensionExample implements ExtensionBuilder{

    public static final String PROCESS_XML = "/process.xml";

    @Override
    public List<Entity> getEntities(String extensionName, InputStream extensionConfigStream) throws FalconException {
        Entity process;
        try {
            process = (Entity) EntityType.PROCESS.getUnmarshaller().unmarshal(
                    getClass().getResourceAsStream(PROCESS_XML));
        } catch (JAXBException e) {
            throw new FalconException("Failed in unmarshalling the entity");
        }
        List<Entity> entities = new ArrayList<>();
        entities.add(process);
        return entities;
    }

    @Override
    public void validateExtensionConfig(String extensionName, InputStream extensionConfigStream)
        throws FalconException {

    }

    @Override
    public List<Pair<String, Schema>> getOutputSchemas(String extensionName) throws FalconException {
        return null;
    }

    public String toString(String testString) {
        return testString;
    }
}
