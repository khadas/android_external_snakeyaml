/**
 * Copyright (c) 2008-2010, http://code.google.com/p/snakeyaml/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.yaml.snakeyaml.representer;

import junit.framework.TestCase;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Util;
import org.yaml.snakeyaml.Yaml;

public class DumpStackTraceTest extends TestCase {

    public void testJavaStackTrace() {
        Yaml yaml = new Yaml();
        String input = Util.getLocalResource("representer/stacktrace1.txt");
        String result = yaml.dump(input);
        // System.out.println(result);
        assertEquals(result, yaml.dump(yaml.load(result)));
    }

    public void testJavaStackTraceWithNoSpecialCharacters() {
        DumperOptions options = new DumperOptions();
        options.setWidth(50);
        Yaml yaml = new Yaml(options);
        String input = Util.getLocalResource("representer/stacktrace2.txt");
        assertEquals(-1, input.indexOf(':'));
        assertEquals(-1, input.indexOf('\t'));
        String result = yaml.dump(input);
        // System.out.println(result);
        assertEquals(result, yaml.dump(yaml.load(result)));
    }

    public void testJavaStackTraceWithTabs() {
        Yaml yaml = new Yaml();
        String input = Util.getLocalResource("representer/stacktrace3.txt");
        assertEquals(-1, input.indexOf(':'));
        assertTrue("Tabs must be used.", input.indexOf('\t') > 0);
        String result = yaml.dump(input);
        // System.out.println(result);
        assertEquals(result, yaml.dump(yaml.load(result)));
    }

    public void testJavaStackTrace2() {
        Yaml yaml = new Yaml();
        String input = Util.getLocalResource("representer/stacktrace1.txt");
        assertTrue("Double quote must be used.", input.indexOf('"') > 0);
        assertTrue("Colon must be used.", input.indexOf(':') > 0);
        assertTrue("Tabs must be used.", input.indexOf('\t') > 0);
        String result = (String) yaml.dump(input);
        // System.out.println(result);
        String etalon = Util.getLocalResource("representer/stacktrace1.yaml");
        assertFalse(etalon.equals(result));
        // TODO stacktrace serialisation can be improved
        // http://code.google.com/p/snakeyaml/issues/detail?id=66)
        // assertEquals(etalon, result);
    }
}
